# Processador de Arquivos B3

## Visão Geral

Este projeto é uma aplicação Java Spring Boot, projetada para monitorar um diretório específico e processar automaticamente arquivos de texto plano da B3. Utiliza a arquitetura limpa (Clean Architecture) e o Spring Batch para garantir um processamento robusto, escalável e resiliente.

## Arquitetura e Princípios de Design

A aplicação foi construída com base nos seguintes princípios:

-   **Clean Architecture**: O código está organizado em camadas (domínio, aplicação, infraestrutura) com dependências que fluem do exterior para o interior. O domínio é o núcleo, livre de acoplamento com frameworks externos.
-   **Spring Batch**: Utilizado para gerenciar o processamento em lote, dividindo-o em etapas de leitura, processamento e escrita. Isso oferece recursos de restartability, monitoramento e tratamento de erros.
-   **Inversão de Dependência (DIP)**: A lógica de negócio depende de abstrações (interfaces), e não de implementações concretas de frameworks.
-   **Imutabilidade**: O uso de Java Records para a camada de domínio garante que os objetos de negócio sejam imutáveis, o que reduz efeitos colaterais e torna o código mais seguro em ambientes concorrentes.
-   **Separação de Preocupações (SoC)**: Cada classe tem uma única responsabilidade. As configurações são externalizadas, a lógica de negócio reside no domínio, e as adaptações de framework estão na camada de infraestrutura.

## Pré-requisitos

-   **Java 24** ou superior
-   **Maven** 3.9.11 ou superior

## Estrutura do Projeto

-   `src/main/java/com/ricardococati/processor/domain`: Contém os modelos de negócio puros (Ex: `TituloCdi`).
-   `src/main/java/com/ricardococati/processor/application`: Contém os casos de uso de negócio, que orquestram a lógica da aplicação (Ex: `ProcessB3FileUseCase`).
-   `src/main/java/com/ricardococati/processor/infrastructure`: Contém os adaptadores para frameworks e a lógica de infraestrutura (acesso a dados, leitores/escritores do Spring Batch, configurações).
-   `src/main/resources/application.properties`: Contém as configurações de ambiente.

## Configuração

A única configuração necessária é o caminho do diretório que será monitorado para novos arquivos. Edite o arquivo `src/main/resources/application.properties` e defina o caminho:

```properties
# O caminho do diretório que será monitorado
b3.file.path=/caminho/do/seu/diretorio
```

Importante: Certifique-se de que o diretório especificado exista e que a aplicação tenha permissões de leitura e escrita.

Execução
Para executar a aplicação, siga os passos abaixo:

Clone o repositório (ou navegue até a pasta do projeto).

Compile o projeto com Maven:

```bash

mvn clean package

```

Execute o arquivo JAR gerado:

```bash

java -jar target/b3-file-processor-0.0.1-SNAPSHOT.jar

```

A aplicação irá iniciar o monitoramento do diretório configurado. Sempre que um novo arquivo for adicionado, o processo de leitura, processamento e escrita será acionado automaticamente.