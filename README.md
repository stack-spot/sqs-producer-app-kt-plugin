# **SQS Producer Kotlin Plugin** 

- **Descrição:** O plugin de `SQS Producer` tem como objetivo provisionar a infraestrutura do SQS via CDK, e deixar a aplicação pronta para produzir mensagens em uma fila da AWS.
- **Categoria:** Mensageria. 
- **Stack:** zup-kotlin-stack.
- **Criado em:** 30/03/2022.
- **Última atualização:** 30/03/2022.
- **Download:** https://github.com/stack-spot/sqs-producer-app-kt-plugin.git

## **Visão Geral**

O **sqs-producer-app-kt-plugin** é um plugin que tem como objetivo o serviço de filas de mensagens gerenciado que permite o desacoplamento e a escalabilidade de microsserviços. Ao adicionar o plugin na aplicação é possível informar o nome da fila onde as mensagens serão produzidas.

### **Pré-requisitos**
- Para utilizar esse plugin, é necessário:
  -  Instalação do STK CLI da StackSpot.
  -  Importar a stack `zup-kotlin-stack` através do STK CLI.
     - `stk import https://github.com/stack-spot/zup-kotlin-stack`   
  - Ter uma aplicação criada pelo template da stack importada.
- Possuir Java 11 instalado na máquina.

### **Como o plugin funciona** 

Ao aplicar o plugin, serão adicionados os recursos necessários para que seja possível provisionar a infraestrutura do SQS via CDK na cloud, além de configurar um `producer` de SQS na aplicação e as suas dependências necessárias.
  - **Infraestrutura:** Utilizando CDK o plugin entrega uma [NestedStack](https://docs.aws.amazon.com/cdk/api/v2/docs/aws-cdk-lib.NestedStack.html) que provisiona na Cloud da AWS a criação da fila informada.
  - **App:** Como facilitador, o plugin entrega uma classe definida como um serviço (@Service), que contém um `producer` que já está preparado para produzir dados na fila informada via `input`, porém o desenvolvedor tem total liberdade para customizar essa classe e o método producer conforme sua necessidade. Para mais informações sobre esse recurso acesse [stackspot - SqsProducer.kt](https://github.com/stack-spot/sqs-producer-app-kt-plugin/blob/main/templates/app/src/main/kotlin/group_id_folder/producer/SqsProducer.kt).

### **Como adicionar o plugin na aplicação?**

Para aplicar o plugin, primeiramente é necessário ter executado os passos do [Pré-requisitos](#Pré-requisitos). Após isso, crie sua a aplicação utilizando o CLI da StackSpot, acesse a pasta raiz do projeto e execute o comando abaixo para aplicar o plugin:
 
`stk apply plugin zup-kotlin-stack/sqs-producer-app-kt-plugin`


## **Configuração**

### **Inputs**

Os inputs necessários para utilizar o plugin são:
| **Campo**               | **Valor**  | **Parâmetro** | **Descrição**                                                                                      |
| :-----------------------| :----------| :-------------|:---------------------------------------------------------------------------------------------------|
| sqs_producer_queue_name | N/A        | N/A           | Nome da fila que será configurada no AWS SQS e será o destino das mensagens enviadas pelo producer |
