# Sistema de Locação de Veículos

Sistema completo de locação de veículos desenvolvido em Java com Hibernate para persistência de dados em MySQL.

## 📋 Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🗄️ Configuração do Banco de Dados

### 1. Instalar MySQL

Certifique-se de que o MySQL está instalado e rodando na sua máquina.

### 2. Criar usuário (opcional)

Se desejar, crie um usuário específico para a aplicação:

```sql
CREATE USER 'locacao_user'@'localhost' IDENTIFIED BY 'senha123';
GRANT ALL PRIVILEGES ON locacao_veiculos.* TO 'locacao_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. O banco será criado automaticamente

A aplicação está configurada para criar o banco automaticamente através da URL:
```
jdbc:mysql://localhost:3306/locacao_veiculos?createDatabaseIfNotExist=true
```

## ⚙️ Configuração do Projeto

### 1. Estrutura de Diretórios

```
locacao-veiculos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── locacao/
│   │   │           ├── model/           # Entidades
│   │   │           │   ├── Marca.java
│   │   │           │   ├── Modelo.java
│   │   │           │   ├── Categoria.java
│   │   │           │   ├── Veiculo.java
│   │   │           │   ├── Manutencao.java
│   │   │           │   ├── Cliente.java
│   │   │           │   ├── Contato.java
│   │   │           │   ├── Usuario.java
│   │   │           │   ├── Endereco.java
│   │   │           │   ├── ContratoLocacao.java
│   │   │           │   ├── Locacao.java
│   │   │           │   ├── Ocorrencia.java
│   │   │           │   ├── Pagamento.java
│   │   │           │   ├── StatusVeiculo.java
│   │   │           │   ├── StatusLocacao.java
│   │   │           │   └── TipoOcorrencia.java
│   │   │           ├── util/
│   │   │           │   └── HibernateUtil.java
│   │   │           └── Main.java
│   │   └── resources/
│   │       └── hibernate.properties
│   └── test/
│       └── java/
└── pom.xml
```

### 2. Configurar hibernate.properties

Edite o arquivo `src/main/resources/hibernate.properties` com suas credenciais:

```properties
hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
hibernate.connection.url=jdbc:mysql://localhost:3306/locacao_veiculos?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Sao_Paulo
hibernate.connection.username=root
hibernate.connection.password=SUA_SENHA_AQUI
hibernate.hbm2ddl.auto=update
hibernate.show_sql=true
hibernate.format_sql=true
hibernate.current_session_context_class=thread
hibernate.connection.CharSet=utf8mb4
hibernate.connection.characterEncoding=utf8mb4
hibernate.connection.useUnicode=true
```

**⚠️ IMPORTANTE:** Altere `hibernate.connection.password` para sua senha do MySQL!

## 🚀 Como Executar

### Opção 1: Via Maven (Linha de Comando)

1. **Baixar dependências:**
```bash
mvn clean install
```

2. **Executar a aplicação:**
```bash
mvn exec:java
```

### Opção 2: Via IDE

1. Importe o projeto como projeto Maven
2. Aguarde o download das dependências
3. Execute a classe `Main.java`

### Opção 3: Gerar JAR executável

```bash
mvn clean package
java -jar target/locacao-veiculos-1.0.0.jar
```

## 📊 Operações Implementadas

A aplicação demonstra as seguintes operações:

### ✅ 1. Inserir Marca com Modelos
- Cria uma marca Toyota
- Adiciona modelos Corolla e Camry
- Associa à categoria Sedan

### ✅ 2. Inserir Veículo
- Cria um veículo Toyota Corolla
- Placa: ABC1234
- Status: DISPONIVEL

### ✅ 3. Criar Locação com Ocorrências
- Cadastra cliente completo com endereço
- Cria funcionário
- Gera contrato de locação
- Registra locação com 2 ocorrências

### ✅ 4. Consultar Locações por Cliente
- Busca todas as locações de um cliente específico
- Exibe detalhes completos

### ✅ 5. Atualizar Status do Veículo
- Altera status de LOCADO para MANUTENCAO
- Atualiza quilometragem

### ✅ 6. Excluir Ocorrência
- Remove uma ocorrência específica do banco

## 🗂️ Modelo de Dados

O sistema implementa o seguinte modelo:

```
Marca (1) ──→ (N) Modelo (N) ←── (1) Categoria
                    │
                    │ (1)
                    ↓
                 Veiculo (N)
                    │
                    ├─→ (N) Manutencao
                    │
                    │ (1)
                    ↓
                 Locacao (N)
                    │
                    ├─→ (N) Ocorrencia
                    ├─→ (N) Pagamento
                    │
                    │ (N)
                    ↓
            ContratoLocacao (1)
                    │
                    ├─→ (1) Cliente ──→ (N) Contato
                    │           │
                    │           └─→ (1) Endereco
                    │
                    └─→ (1) Usuario (Funcionário)
                                │
                                └─→ (1) Endereco
```

## 📝 Entidades e Relacionamentos

| Entidade | Relacionamentos |
|----------|----------------|
| **Marca** | OneToMany → Modelo |
| **Modelo** | ManyToOne → Marca, ManyToOne → Categoria, OneToMany → Veiculo |
| **Categoria** | OneToMany → Modelo |
| **Veiculo** | ManyToOne → Modelo, OneToMany → Locacao, OneToMany → Manutencao |
| **Locacao** | ManyToOne → Veiculo, ManyToOne → ContratoLocacao, OneToMany → Ocorrencia, OneToMany → Pagamento |
| **ContratoLocacao** | ManyToOne → Cliente, ManyToOne → Usuario, OneToMany → Locacao |
| **Cliente** | OneToMany → Contato, OneToMany → ContratoLocacao, OneToOne → Endereco |
| **Usuario** | OneToOne → Endereco |

## 🔧 Configurações do Hibernate

### Estratégias de Geração de ID
Todas as entidades usam `@GeneratedValue(strategy = GenerationType.IDENTITY)` para auto-incremento.

### Cascade Types
- **ALL**: Marca → Modelo, Cliente → Contato
- **ALL com orphanRemoval**: Locacao → Ocorrencia
- **Padrão**: Demais relacionamentos

### Hibernate Properties

| Propriedade | Valor | Descrição |
|-------------|-------|-----------|
| `hibernate.hbm2ddl.auto` | update | Atualiza schema automaticamente |
| `hibernate.show_sql` | true | Exibe SQL no console |
| `hibernate.format_sql` | true | Formata SQL para melhor leitura |
| `hibernate.dialect` | MySQL8Dialect | Dialeto MySQL 8 |

## 🎯 Recursos Implementados

- ✅ Mapeamento completo de entidades com JPA/Hibernate
- ✅ Relacionamentos bidirecionais e unidirecionais
- ✅ Operações CRUD completas
- ✅ Queries HQL para consultas complexas
- ✅ Gerenciamento de transações
- ✅ Tratamento de exceções
- ✅ Configuração via hibernate.properties
- ✅ Enums para status e tipos
- ✅ Tipos de dados adequados (BigDecimal, LocalDate, LocalDateTime)

## 🐛 Troubleshooting

### Erro de conexão com MySQL

```
Could not create connection to database server
```

**Solução:**
- Verifique se o MySQL está rodando
- Confirme usuário e senha no `hibernate.properties`
- Teste conexão: `mysql -u root -p`

### Erro de timezone

```
The server time zone value 'XXX' is unrecognized
```

**Solução:** Adicione `serverTimezone=America/Sao_Paulo` na URL de conexão (já incluído).

### Schema não é criado

**Solução:**
- Verifique se `hibernate.hbm2ddl.auto=update` está configurado
- Confirme permissões do usuário MySQL
- Use `createDatabaseIfNotExist=true` na URL

### Dependências não baixam

**Solução:**
```bash
mvn clean
mvn dependency:purge-local-repository
mvn install
```

## 📦 Dependências Maven

```xml
- Hibernate Core 5.6.15.Final
- MySQL Connector 8.0.33
- JPA API 2.2
- SLF4J Simple 1.7.36
- Javafx 0.0.8
```

## 👨‍💻 Exemplos de Uso

### Criar nova categoria

```java
Categoria suv = new Categoria("SUV", "Veículos utilitários esportivos", new BigDecimal("250.00"));
session.save(suv);
```

### Buscar veículos disponíveis

```java
Query<Veiculo> query = session.createQuery(
    "FROM Veiculo v WHERE v.status = :status", Veiculo.class
);
query.setParameter("status", StatusVeiculo.DISPONIVEL);
List<Veiculo> disponiveis = query.getResultList();
```

### Calcular total de locações de um cliente

```java
String hql = "SELECT SUM(l.valorTotal) FROM Locacao l " +
             "JOIN l.contrato c WHERE c.cliente.id = :clienteId";
Query<BigDecimal> query = session.createQuery(hql, BigDecimal.class);
query.setParameter("clienteId", clienteId);
BigDecimal total = query.uniqueResult();
```
