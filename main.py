from yaml import safe_load

from templateframework.metadata import Metadata
from templateframework.runner import run
from templateframework.template import Template

def merge_entrypoint(metadata: Metadata):
    docker_compose_file = metadata.target_path.joinpath('app', 'docker-compose.yml')
    merged_entrypoint = ["./wait-for.sh","-t","30","localstack:4566","--","java","-jar","app.jar"]

    if 'previous_entrypoint' in metadata.computed_inputs:
        if './wait-for.sh' not in metadata.computed_inputs['previous_entrypoint']:
            merged_entrypoint = ["./wait-for.sh","-t","30","localstack:4566"] + metadata.computed_inputs['previous_entrypoint']
        else:
            merged_entrypoint = metadata.computed_inputs['previous_entrypoint']

    original = docker_compose_file.read_text()
    result = original.replace('call_wait_for_here', '["'+'","'.join(merged_entrypoint)+'"]')
    docker_compose_file.write_text(result)

def save_current_entrypoint(metadata: Metadata):
    docker_compose_yml = safe_load(metadata.target_path.joinpath('app', 'docker-compose.yml').read_text())
    project_name = metadata.global_inputs["project_name"]
    if 'services' in docker_compose_yml and project_name in docker_compose_yml['services'] and 'entrypoint' in docker_compose_yml['services'][project_name]:
        metadata.computed_inputs["previous_entrypoint"] = docker_compose_yml['services'][project_name]["entrypoint"]
    return metadata

class CleanEmptyMigrationSettings(Template):
    def pre_hook(self, metadata: Metadata) -> Metadata:
        return save_current_entrypoint(metadata)

    def post_hook(self, metadata: Metadata):
        merge_entrypoint(metadata)


if __name__ == '__main__':
    run(CleanEmptyMigrationSettings())