# 💡 Projeto de Operações Matemáticas em Spring Boot

Este projeto tem como objetivo implementar operações matemáticas básicas utilizando o Spring Boot. Nele, explorei conceitos fundamentais do framework e suas anotações, além de aprender a estruturar uma aplicação de forma eficiente.

## 👨🏽‍💻 Desenvolvedor

+ Vagner Ferreira Lima Junior: [@vagnerflj](https://github.com/vagnerflj)

## Sumário

- [Teoria do Projeto](#teoria-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Exemplos de Código](#exemplos-de-código)
- [Como Executar](#como-executar)
- [Contribuição](#contribuição)

## Teoria do Projeto

- Implementação de operações matemáticas (soma, subtração, multiplicação e divisão)
- Compreensão das principais anotações do Spring Boot como `@RestController`, `@RequestMapping`, `@GetMapping`, e `@PostMapping`
- Estruturação da aplicação em camadas: Controller, Service e Repository

## Tecnologias Utilizadas

- **Java**: Versão 17 ou superior
- **Spring Boot**: 3.x
- **JUnit 5**: Para testes unitários
- **Mockito**: Para criação de mocks
- **Maven**: Para gerenciamento de dependências

## Funcionalidades

- Implementação de operações matemáticas básicas
- Criação de endpoints REST para realizar cálculos
- Validação de entradas de dados
- Estrutura clara e organizada, seguindo boas práticas de desenvolvimento

## Estrutura do Projeto
```
└── src
├── main
│   ├── java
│   │   └── com
│   │       └── exemplo
│   │           └── demo
│   │               ├── exception
│   │               │   └── ExceptionResponse
│   │               │   └──UnsupportedMathOperationException
│   │               ├── Controller
│   │                   └── MathController.java
│   │                   └── Startup
│   │                   
│   └── resources
│       └── application.properties
└── test
└── java
└── com
└── exemplo


```


## Exemplos de Código

### Controller

```java

@RestController
public class MathController {

	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double result = covertToDouble(numberOne) + covertToDouble(numberTwo);
		return result;
	}
```

## Como Executar

```java
## 1. Clone o repositório:

git clone https://github.com/vagnerflj/SpringBoot.git
cd SpringBoot

## 2. Execute a aplicação:

./mvnw spring-boot:run

## 3. Acesse os endpoints:

GET http://localhost:8080/sum/1/2

```
