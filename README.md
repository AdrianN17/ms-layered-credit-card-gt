# ms-layered-credit-card-gt

Microservicio de tarjeta de crédito con arquitectura **Layered + Ports & Adapters (Hexagonal Híbrida)**.

- **Persistencia relacional:** SQL Server (JPA/Hibernate)
- **Persistencia NoSQL:** MongoDB / Azure Cosmos DB (según perfil)
- **Generación de ID:** Snowflake Algorithm
- **AOP:** `@TransactionalUseCase` intercepta métodos en API
- **Generación de API:** OpenAPI Generator (`spring`, `interfaceOnly`)

---

## Diagrama de Arquitectura — C4 Level 4 (Code Diagram)

```mermaid
classDiagram
    %% ================================================================
    %% PRESENTATION LAYER
    %% ================================================================
    namespace PresentationLayer {
        class CardApi {
            <<API Interface>>
            +initiateCard(InitiateCardRequest, BindingResult) ResponseEntity
            +controlCard(Long) ResponseEntity
            +retrieveBalance(Long) ResponseEntity
        }
        class CardController {
            <<RestController>>
            -delegate CardDelegate
            +initiateCard()
            +controlCard()
            +retrieveBalance()
        }
        class CardDelegate {
            <<Delegate Interface>>
            +initiateCard(InitiateCardRequest, BindingResult) ResponseEntity
            +controlCard(Long) ResponseEntity
            +retrieveBalance(Long) ResponseEntity
        }
        class CardDelegateImpl {
            <<Component>>
            -cardService CardService
            -balanceService BalanceService
            -benefitService BenefitService
            -cardMapper CardMapper
            -balanceMapper BalanceMapper
            -benefitMapper BenefitMapper
            -cardSummaryMapper CardSummaryMapper
            -idGenerate IdGenerate
            +initiateCard()
            +controlCard()
            +retrieveBalance()
        }
        class ConsumptionApi {
            <<API Interface>>
            +initiateConsumption(Long, InitiateConsumptionRequest, BindingResult) ResponseEntity
            +controlConsumption(Long, UUID) ResponseEntity
            +exchangeConsumption(Long, UUID, ExchangeConsumptionRequest, BindingResult) ResponseEntity
            +retrieveConsumption(Long, LocalDate, LocalDate) ResponseEntity
        }
        class ConsumptionController {
            <<RestController>>
            -delegate ConsumptionDelegate
            +initiateConsumption()
            +controlConsumption()
            +exchangeConsumption()
            +retrieveConsumption()
        }
        class ConsumptionDelegate {
            <<Delegate Interface>>
            +initiateConsumption() ResponseEntity
            +controlConsumption() ResponseEntity
            +exchangeConsumption() ResponseEntity
            +retrieveConsumption() ResponseEntity
        }
        class ConsumptionDelegateImpl {
            <<Component>>
            -benefitService BenefitService
            -balanceService BalanceService
            -cardService CardService
            -consumptionService ConsumptionService
            -consumptionMapper ConsumptionMapper
            -currencyService CurrencyService
            +initiateConsumption()
            +controlConsumption()
            +exchangeConsumption()
            +retrieveConsumption()
        }
        class PaymentApi {
            <<API Interface>>
            +initiatePayment(Long, InitiatePaymentRequest, BindingResult) ResponseEntity
            +controlPayment(Long, UUID) ResponseEntity
            +retrievePayment(Long, LocalDate, LocalDate) ResponseEntity
        }
        class PaymentController {
            <<RestController>>
            -delegate PaymentDelegate
            +initiatePayment()
            +controlPayment()
            +retrievePayment()
        }
        class PaymentDelegate {
            <<Delegate Interface>>
            +initiatePayment() ResponseEntity
            +controlPayment() ResponseEntity
            +retrievePayment() ResponseEntity
        }
        class PaymentDelegateImpl {
            <<Component>>
            -benefitService BenefitService
            -balanceService BalanceService
            -cardService CardService
            -paymentService PaymentService
            -paymentMapper PaymentMapper
            -currencyService CurrencyService
            +initiatePayment()
            +controlPayment()
            +retrievePayment()
        }
        class InitiateCardRequest { <<Request DTO>> }
        class InitiateConsumptionRequest { <<Request DTO>> }
        class ExchangeConsumptionRequest { <<Request DTO>> }
        class InitiatePaymentRequest { <<Request DTO>> }
        class RetrieveBalance200Response { <<Response DTO>> }
        class RetrieveConsumption200Response { <<Response DTO>> }
        class RetrievePayment200Response { <<Response DTO>> }
        class Long202Response { <<Response DTO>> }
        class UUID202Response { <<Response DTO>> }
        class UUIDList202Response { <<Response DTO>> }
    }

    %% ================================================================
    %% APPLICATION / SERVICE LAYER
    %% ================================================================
    namespace ApplicationLayer {
        class CardService {
            <<Service Interface>>
            +save(CardDtoRequest)
            +find(Long) CardDtoResponse
            +delete(Long)
            +getRatio(CategoryCardEnum) BigDecimal
            +validate(CardStatusEnum)
        }
        class CardServiceImpl {
            <<Service>>
            -cardJpaRepository CardJpaRepository
            -cardAccountJpaRepository CardAccountJpaRepository
            -cardVOJpaRepository CardVOJpaRepository
            -cardMapper CardMapper
            -cardAccountMapper CardAccountMapper
            -cardSummaryMapper CardSummaryMapper
            +save(CardDtoRequest)
            +find(Long) CardDtoResponse
            +delete(Long)
            +getRatio(CategoryCardEnum) BigDecimal
            +validate(CardStatusEnum)
        }
        class ConsumptionService {
            <<Service Interface>>
            +save(ConsumptionRequestDto)
            +findAll(String, LocalDate, LocalDate) List
            +split(Integer, String, UUID, BigDecimal) List
            +delete(UUID)
            +get(UUID) ConsumptionResponseDto
        }
        class ConsumptionServiceImpl {
            <<Service>>
            -consumptionRepository ConsumptionRepository
            -consumptionMapper ConsumptionMapper
            +save()
            +findAll()
            +split()
            +delete()
            +get()
        }
        class PaymentService {
            <<Service Interface>>
            +save(PaymentRequestDto)
            +validate(BigDecimal, BigDecimal, LocalDate, LocalDate, PaymentRequestDto)
            +get(UUID) PaymentResponseDto
            +findAll(String, LocalDate, LocalDate) List
            +delete(UUID)
        }
        class PaymentServiceImpl {
            <<Service>>
            -paymentRepository PaymentRepository
            -paymentMapper PaymentMapper
            +save()
            +validate()
            +get()
            +findAll()
            +delete()
        }
        class BalanceService {
            <<Service Interface>>
            +save(BalanceDtoRequest)
            +delete(Long)
            +apply(Long, BigDecimal, BalanceUseCaseEnum)
            +cancel(Long, BigDecimal, BalanceUseCaseEnum)
        }
        class BalanceServiceImpl {
            <<Service>>
            -balanceJpaRepository BalanceJpaRepository
            -balanceMapper BalanceMapper
            -balanceUseCaseFactory BalanceUseCaseFactory
            +save()
            +delete()
            +apply()
            +cancel()
            -updateCardStatus(Long)
            -findActiveOrThrow(Long) BalanceEntity
        }
        class BenefitService {
            <<Service Interface>>
            +save(BenefitRequestDto)
            +delete(Long)
            +accumulate(BigDecimal, BigDecimal, Long)
            +discount(BigDecimal, Integer, Long) BigDecimal
        }
        class BenefitServiceImpl {
            <<Service>>
            -benefitJpaRepository BenefitJpaRepository
            -benefitMapper BenefitMapper
            +save()
            +delete()
            +accumulate()
            +discount()
        }
        class CurrencyService {
            <<Service Interface>>
            +get(CurrencyEnum, CurrencyEnum, BigDecimal) BigDecimal
        }
        class CurrencyServiceImpl {
            <<Service>>
            -currencyExchangeRateRepository CurrencyExchangeRateRepository
            +get(CurrencyEnum, CurrencyEnum, BigDecimal) BigDecimal
        }
        class CardDtoRequest { <<DTO>> }
        class CardDtoResponse { <<DTO>> }
        class ConsumptionRequestDto { <<DTO>> }
        class ConsumptionResponseDto { <<DTO>> }
        class PaymentRequestDto { <<DTO>> }
        class PaymentResponseDto { <<DTO>> }
        class BalanceDtoRequest { <<DTO>> }
        class BenefitRequestDto { <<DTO>> }
        class ExchangeRateApiDto { <<DTO>> }
        class CurrencyDto { <<DTO>> }
    }

    %% ================================================================
    %% USE CASES
    %% ================================================================
    namespace UseCases {
        class PaymentUseCase {
            <<Use Case Interface>>
            +close()
            +validateIfPaymentIsPossible(BigDecimal, BigDecimal, LocalDate, LocalDate)
        }
        class AbstractPaymentUseCase {
            <<abstract>>
            #amount BigDecimal
            #paymentApprobationDate LocalDate
        }
        class NormalPaymentUseCase {
            <<Use Case>>
            +close()
            +validateIfPaymentIsPossible()
        }
        class TotalPaymentUseCase {
            <<Use Case>>
            +close()
            +validateIfPaymentIsPossible()
        }
        class MinimumPaymentUseCase {
            <<Use Case>>
            +close()
            +validateIfPaymentIsPossible()
        }
        class PrepaymentUseCase {
            <<Use Case>>
            +close()
            +validateIfPaymentIsPossible()
        }
        class PaymentUseCaseFactory {
            <<Factory>>
            +create(BigDecimal, LocalDate, CategoryPaymentEnum) PaymentUseCase$
            -build(AbstractPaymentUseCase, BigDecimal, LocalDate) PaymentUseCase$
        }
        class BalanceUseCase {
            <<Use Case Interface>>
            +apply(BigDecimal)
            +cancel(BigDecimal)
        }
        class ConsumptionBalanceUseCase {
            <<Component Use Case>>
            -balanceJpaRepository BalanceJpaRepository
            -entity BalanceEntity
            +apply(BigDecimal)
            +cancel(BigDecimal)
            +withEntity(BalanceEntity) ConsumptionBalanceUseCase
        }
        class PaymentBalanceUseCase {
            <<Component Use Case>>
            -balanceJpaRepository BalanceJpaRepository
            -entity BalanceEntity
            +apply(BigDecimal)
            +cancel(BigDecimal)
            +withEntity(BalanceEntity) PaymentBalanceUseCase
        }
        class BalanceUseCaseFactory {
            <<Component Factory>>
            -consumptionUseCase ConsumptionBalanceUseCase
            -paymentUseCase PaymentBalanceUseCase
            +create(BalanceUseCaseEnum, BalanceEntity) BalanceUseCase
        }
    }

    %% ================================================================
    %% DOMAIN
    %% ================================================================
    namespace Domain {
        class GenericEntity {
            <<MappedSuperclass>>
            #createdDate LocalDateTime
            #updatedDate LocalDateTime
            #status StatusEnum
            +softDelete()
        }
        class CardEntity {
            <<Entity Cards>>
            -cardId Long
            -typeCard TypeCardEnum
            -categoryCard CategoryCardEnum
        }
        class CardAccountEntity {
            <<Entity CardAccounts>>
            -cardAccountId Long
            -cardId Long
            -creditTotal BigDecimal
            -debtTax BigDecimal
            -currency CurrencyEnum
            -paymentDate Short
            -cardStatus CardStatusEnum
        }
        class BalanceEntity {
            <<Entity balances>>
            -idBalance Long
            -cardId Long
            -totalAmount BigDecimal
            -availableAmount BigDecimal
            -oldAmount BigDecimal
            -currency CurrencyEnum
            -startDate LocalDate
            -endDate LocalDate
        }
        class BenefitEntity {
            <<Entity Benefits>>
            -idBenefit Long
            -cardId Long
            -hasDiscount Boolean
            -totalPoints Integer
            -multiplierPoints BigDecimal
        }
        class ConsumptionEntity {
            <<Entity Interface>>
            +getConsumptionId() UUID
            +getCardId() String
            +getSellerName() String
            +getCurrency() CurrencyEnum
            +getAmount() BigDecimal
            +getConsumptionDate() LocalDateTime
            +getConsumptionApprobationDate() LocalDateTime
        }
        class ConsumptionEntityMongo {
            <<Document Consumptions>>
            -consumptionId UUID
            -cardId String
            -sellerName String
            -currency CurrencyEnum
            -amount BigDecimal
            -consumptionDate LocalDateTime
            -consumptionApprobationDate LocalDateTime
        }
        class ConsumptionEntityCosmos {
            <<Container Consumptions>>
            -consumptionId UUID
            -cardId String
            -sellerName String
            -currency CurrencyEnum
            -amount BigDecimal
            -consumptionDate LocalDateTime
            -consumptionApprobationDate LocalDateTime
        }
        class PaymentEntity {
            <<Entity Interface>>
            +getPaymentId() UUID
            +getCardId() String
            +getAmount() BigDecimal
            +getCurrency() CurrencyEnum
            +getPaymentDate() LocalDateTime
            +getPaymentApprobationDate() LocalDateTime
            +getChannel() ChannelPaymentEnum
            +getCategory() CategoryPaymentEnum
        }
        class PaymentEntityMongo { <<Document Payments>> }
        class PaymentEntityCosmos { <<Container Payments>> }
        class Amount {
            <<Value Object>>
            -currency CurrencyEnum
            -exchangeRate BigDecimal
            -amount BigDecimal
            +create(CurrencyEnum, BigDecimal, BigDecimal) Amount$
            +mas(Amount) Amount
            +menos(Amount) Amount
            +dividir(Integer) Amount
            +fraccionar(Integer, BigDecimal) Amount
            +descuento(BigDecimal) Amount
            +convertir(Amount, Amount) Amount$
        }
        class CardStatusEnum {
            <<enumeration>>
            ACTIVE
            IN_DEBT
            INACTIVE
        }
        class CategoryCardEnum {
            <<enumeration>>
            NORMAL
            SILVER
            GOLD
            PLATINUM
            BLACK
            SIGNATURE
            INFINITY
        }
        class TypeCardEnum {
            <<enumeration>>
            VISA
            MASTERCARD
        }
        class CategoryPaymentEnum {
            <<enumeration>>
            NORMAL
            TOTAL
            MINIMO
            ADELANTADO
        }
        class ChannelPaymentEnum { <<enumeration>> }
        class BalanceUseCaseEnum {
            <<enumeration>>
            CONSUMPTION
            PAYMENT
        }
        class CurrencyEnum { <<enumeration>> }
        class StatusEnum {
            <<enumeration>>
            ACTIVE
            INACTIVE
        }
        class ServiceException { <<Exception>> }
        class BadRequestException { <<Exception>> }
        class UnprocessableEntityException { <<Exception>> }
        class InternalServerErrorException { <<Exception>> }
        class BadGatewayException { <<Exception>> }
        class UnauthorizedException { <<Exception>> }
        class RequestValidationException { <<Exception>> }
    }

    %% ================================================================
    %% PORTS (Output)
    %% ================================================================
    namespace Ports {
        class ConsumptionRepository {
            <<Output Port>>
            +save(ConsumptionEntity) ConsumptionEntity
            +findById(UUID) Optional
            +findByCardIdAndConsumptionDateBetween(String, LocalDate, LocalDate) List
            +softDelete(UUID)
        }
        class PaymentRepository {
            <<Output Port>>
            +save(PaymentEntity) PaymentEntity
            +findById(UUID) Optional
            +findByCardIdAndPaymentDateBetween(String, LocalDate, LocalDate) List
            +softDelete(UUID)
        }
        class CurrencyExchangeRateRepository {
            <<Output Port>>
            +getExchangeRate(CurrencyEnum, CurrencyEnum) BigDecimal
        }
        class IdGenerate {
            <<Functional Interface Port>>
            +load() Optional
        }
    }

    %% ================================================================
    %% ADAPTERS (Outbound)
    %% ================================================================
    namespace Adapters {
        class ConsumptionMongoRepositoryAdapter {
            <<Component Profile-old>>
            -repository ConsumptionMongoRepository
            +save(ConsumptionEntity) ConsumptionEntity
            +findById(UUID) Optional
            +findByCardIdAndConsumptionDateBetween() List
            +softDelete(UUID)
        }
        class ConsumptionCosmosRepositoryAdapter {
            <<Component Profile-new>>
            -repository ConsumptionCosmosRepository
            +save(ConsumptionEntity) ConsumptionEntity
            +findById(UUID) Optional
            +findByCardIdAndConsumptionDateBetween() List
            +softDelete(UUID)
        }
        class PaymentMongoRepositoryAdapter {
            <<Component Profile-old>>
            -repository PaymentMongoRepository
            +save(PaymentEntity) PaymentEntity
            +findById(UUID) Optional
            +findByCardIdAndPaymentDateBetween() List
            +softDelete(UUID)
        }
        class PaymentCosmosRepositoryAdapter {
            <<Component Profile-new>>
            -repository PaymentCosmosRepository
            +save(PaymentEntity) PaymentEntity
            +findById(UUID) Optional
            +findByCardIdAndPaymentDateBetween() List
            +softDelete(UUID)
        }
        class CurrencyNewWSRepository {
            <<Repository Profile-new>>
            -restClient RestClient
            -apiKey String
            +getExchangeRate(CurrencyEnum, CurrencyEnum) BigDecimal
        }
        class CurrencyOldWSRepository {
            <<Repository Profile-old>>
            -restClient RestClient
            +getExchangeRate(CurrencyEnum, CurrencyEnum) BigDecimal
        }
        class SnowflakeGenerator {
            <<Component>>
            -machineId long
            -sequence long
            -lastTimestamp long
            +load() Optional
            -nextId() Long
        }
    }

    %% ================================================================
    %% INFRASTRUCTURE
    %% ================================================================
    namespace Infrastructure {
        class GenericJpaRepository {
            <<NoRepositoryBean>>
            +findActiveById(ID) Optional
        }
        class CardJpaRepository { <<Repository>> }
        class CardAccountJpaRepository { <<Repository>> }
        class CardVOJpaRepository {
            <<Repository>>
            +getCardAllProjectionByCardId(Long) Optional
            +getCardCurrencyProjectionByCardId(Long) Optional
        }
        class BalanceJpaRepository {
            <<Repository>>
            +findActiveByCardId(Long) Optional
            +callUpdateCardStatus(Long, BigDecimal)
        }
        class BenefitJpaRepository {
            <<Repository>>
            +findActiveByCardId(Long) Optional
        }
        class GenericMongoRepository {
            <<NoRepositoryBean>>
            +findActiveById(ID, String) Optional
        }
        class GenericCosmosRepository {
            <<NoRepositoryBean>>
            +findActiveById(ID, String) Optional
        }
        class ConsumptionMongoRepository {
            <<Repository>>
            +findByCardIdAndConsumptionDateBetween(String, LocalDateTime, LocalDateTime) List
        }
        class ConsumptionCosmosRepository {
            <<Repository>>
            +findByCardIdAndConsumptionDateBetween(String, LocalDateTime, LocalDateTime) List
        }
        class PaymentMongoRepository {
            <<Repository>>
            +findByCardIdAndPaymentDateBetween(String, LocalDateTime, LocalDateTime) List
        }
        class PaymentCosmosRepository {
            <<Repository>>
            +findByCardIdAndPaymentDateBetween(String, LocalDateTime, LocalDateTime) List
        }
        class JpaRepositoryConfig { <<Configuration EnableJpaRepositories>> }
        class RestClientConfig {
            <<Configuration>>
            +restClientOld() RestClient
            +restClientNew() RestClient
        }
        class TransactionalUseCaseAspect {
            <<Aspect Component>>
            -transactionManager PlatformTransactionManager
            +manageTransaction(ProceedingJoinPoint) Object
        }
        class GlobalControllAdvice {
            <<ControllerAdvice>>
            +handleMethodArgumentNotValid() ResponseEntity
            +handleBadRequestException() ResponseEntity
            +handleUnprocessableEntityException() ResponseEntity
            +handleBadGatewayException() ResponseEntity
            +handleInternalServerErrorException() ResponseEntity
        }
        class CardMapper { <<Component Mapper>> }
        class CardAccountMapper { <<Component Mapper>> }
        class CardSummaryMapper { <<Component Mapper>> }
        class ConsumptionMapper { <<Component Mapper>> }
        class PaymentMapper { <<Component Mapper>> }
        class BalanceMapper { <<Component Mapper>> }
        class BenefitMapper { <<Component Mapper>> }
        class SQLServer { <<database SQL Server JPA-Hibernate>> }
        class MongoDB { <<database MongoDB>> }
        class CosmosDB { <<database Azure Cosmos DB>> }
        class ExchangeRateAPI { <<external ExchangeRate API new>> }
        class CurrencyOldAPI { <<external Currency WS old>> }
    }

    %% ================================================================
    %% SPRING / AZURE FRAMEWORK (referencias externas)
    %% ================================================================
    namespace SpringFramework {
        class JpaRepository { <<Spring Data JPA>> }
        class MongoRepository { <<Spring Data MongoDB>> }
        class CosmosRepository { <<Azure Spring Data Cosmos>> }
    }

    %% ================================================================
    %% RELACIONES — PRESENTATION LAYER
    %% ================================================================
    CardController ..|> CardApi
    ConsumptionController ..|> ConsumptionApi
    PaymentController ..|> PaymentApi

    CardController --> CardDelegate : delegates to
    ConsumptionController --> ConsumptionDelegate : delegates to
    PaymentController --> PaymentDelegate : delegates to

    CardDelegateImpl ..|> CardDelegate
    ConsumptionDelegateImpl ..|> ConsumptionDelegate
    PaymentDelegateImpl ..|> PaymentDelegate

    CardController ..> InitiateCardRequest : uses
    CardController ..> RetrieveBalance200Response : produces
    ConsumptionController ..> InitiateConsumptionRequest : uses
    ConsumptionController ..> ExchangeConsumptionRequest : uses
    ConsumptionController ..> RetrieveConsumption200Response : produces
    PaymentController ..> InitiatePaymentRequest : uses
    PaymentController ..> RetrievePayment200Response : produces

    %% ================================================================
    %% RELACIONES — DELEGATE → SERVICE
    %% ================================================================
    CardDelegateImpl --> CardService : calls
    CardDelegateImpl --> BalanceService : calls
    CardDelegateImpl --> BenefitService : calls
    CardDelegateImpl --> IdGenerate : calls

    ConsumptionDelegateImpl --> ConsumptionService : calls
    ConsumptionDelegateImpl --> CardService : calls
    ConsumptionDelegateImpl --> BalanceService : calls
    ConsumptionDelegateImpl --> BenefitService : calls
    ConsumptionDelegateImpl --> CurrencyService : calls

    PaymentDelegateImpl --> PaymentService : calls
    PaymentDelegateImpl --> CardService : calls
    PaymentDelegateImpl --> BalanceService : calls
    PaymentDelegateImpl --> BenefitService : calls
    PaymentDelegateImpl --> CurrencyService : calls

    %% ================================================================
    %% RELACIONES — SERVICE IMPL
    %% ================================================================
    CardServiceImpl ..|> CardService
    CardServiceImpl --> CardJpaRepository : uses
    CardServiceImpl --> CardAccountJpaRepository : uses
    CardServiceImpl --> CardVOJpaRepository : uses
    CardServiceImpl --> CardMapper : uses
    CardServiceImpl --> CardAccountMapper : uses
    CardServiceImpl --> CardSummaryMapper : uses

    ConsumptionServiceImpl ..|> ConsumptionService
    ConsumptionServiceImpl --> ConsumptionRepository : port
    ConsumptionServiceImpl --> ConsumptionMapper : uses

    PaymentServiceImpl ..|> PaymentService
    PaymentServiceImpl --> PaymentRepository : port
    PaymentServiceImpl --> PaymentMapper : uses
    PaymentServiceImpl --> PaymentUseCaseFactory : uses

    BalanceServiceImpl ..|> BalanceService
    BalanceServiceImpl --> BalanceJpaRepository : uses
    BalanceServiceImpl --> BalanceMapper : uses
    BalanceServiceImpl --> BalanceUseCaseFactory : uses

    BenefitServiceImpl ..|> BenefitService
    BenefitServiceImpl --> BenefitJpaRepository : uses
    BenefitServiceImpl --> BenefitMapper : uses

    CurrencyServiceImpl ..|> CurrencyService
    CurrencyServiceImpl --> CurrencyExchangeRateRepository : port

    %% ================================================================
    %% RELACIONES — USE CASES
    %% ================================================================
    AbstractPaymentUseCase ..|> PaymentUseCase
    NormalPaymentUseCase --|> AbstractPaymentUseCase
    TotalPaymentUseCase --|> AbstractPaymentUseCase
    MinimumPaymentUseCase --|> AbstractPaymentUseCase
    PrepaymentUseCase --|> AbstractPaymentUseCase

    PaymentUseCaseFactory ..> NormalPaymentUseCase : creates
    PaymentUseCaseFactory ..> TotalPaymentUseCase : creates
    PaymentUseCaseFactory ..> MinimumPaymentUseCase : creates
    PaymentUseCaseFactory ..> PrepaymentUseCase : creates
    PaymentUseCaseFactory ..> PaymentUseCase : returns

    PaymentServiceImpl --> PaymentUseCaseFactory : uses

    ConsumptionBalanceUseCase ..|> BalanceUseCase
    PaymentBalanceUseCase ..|> BalanceUseCase

    ConsumptionBalanceUseCase --> BalanceJpaRepository : uses
    PaymentBalanceUseCase --> BalanceJpaRepository : uses

    BalanceUseCaseFactory --> ConsumptionBalanceUseCase : injects
    BalanceUseCaseFactory --> PaymentBalanceUseCase : injects
    BalanceUseCaseFactory ..> BalanceUseCase : creates

    BalanceServiceImpl --> BalanceUseCaseFactory : uses

    %% ================================================================
    %% RELACIONES — DOMAIN / ENTITIES
    %% ================================================================
    CardEntity --|> GenericEntity
    CardAccountEntity --|> GenericEntity
    BalanceEntity --|> GenericEntity
    BenefitEntity --|> GenericEntity

    ConsumptionEntityMongo --|> GenericEntity
    ConsumptionEntityMongo ..|> ConsumptionEntity
    ConsumptionEntityCosmos --|> GenericEntity
    ConsumptionEntityCosmos ..|> ConsumptionEntity

    PaymentEntityMongo ..|> PaymentEntity
    PaymentEntityCosmos ..|> PaymentEntity

    %% ================================================================
    %% RELACIONES — PORTS → ADAPTERS
    %% ================================================================
    ConsumptionMongoRepositoryAdapter ..|> ConsumptionRepository
    ConsumptionCosmosRepositoryAdapter ..|> ConsumptionRepository
    ConsumptionMongoRepositoryAdapter --> ConsumptionMongoRepository : uses
    ConsumptionCosmosRepositoryAdapter --> ConsumptionCosmosRepository : uses

    PaymentMongoRepositoryAdapter ..|> PaymentRepository
    PaymentCosmosRepositoryAdapter ..|> PaymentRepository
    PaymentMongoRepositoryAdapter --> PaymentMongoRepository : uses
    PaymentCosmosRepositoryAdapter --> PaymentCosmosRepository : uses

    CurrencyNewWSRepository ..|> CurrencyExchangeRateRepository
    CurrencyOldWSRepository ..|> CurrencyExchangeRateRepository

    SnowflakeGenerator ..|> IdGenerate

    %% ================================================================
    %% RELACIONES — INFRASTRUCTURE / JPA
    %% ================================================================
    GenericJpaRepository --|> JpaRepository
    CardJpaRepository --|> GenericJpaRepository
    CardAccountJpaRepository --|> GenericJpaRepository
    CardVOJpaRepository --|> GenericJpaRepository
    BalanceJpaRepository --|> GenericJpaRepository
    BenefitJpaRepository --|> GenericJpaRepository

    GenericMongoRepository --|> MongoRepository
    ConsumptionMongoRepository --|> GenericMongoRepository
    PaymentMongoRepository --|> GenericMongoRepository

    GenericCosmosRepository --|> CosmosRepository
    ConsumptionCosmosRepository --|> GenericCosmosRepository
    PaymentCosmosRepository --|> GenericCosmosRepository

    %% ================================================================
    %% RELACIONES — ADAPTERS → EXTERNAL SYSTEMS
    %% ================================================================
    CardJpaRepository ..> SQLServer : SQL Server JPA
    CardAccountJpaRepository ..> SQLServer : SQL Server JPA
    CardVOJpaRepository ..> SQLServer : SQL Server native query
    BalanceJpaRepository ..> SQLServer : SQL Server JPA + Stored Proc
    BenefitJpaRepository ..> SQLServer : SQL Server JPA

    ConsumptionMongoRepository ..> MongoDB : MongoDB
    PaymentMongoRepository ..> MongoDB : MongoDB

    ConsumptionCosmosRepository ..> CosmosDB : Azure Cosmos DB
    PaymentCosmosRepository ..> CosmosDB : Azure Cosmos DB

    CurrencyNewWSRepository ..> ExchangeRateAPI : REST GET /v6/{key}/latest/{base}
    CurrencyOldWSRepository ..> CurrencyOldAPI : REST GET /{from}

    %% ================================================================
    %% RELACIONES — CONFIG / AOP
    %% ================================================================
    RestClientConfig ..> CurrencyNewWSRepository : provides RestClient
    RestClientConfig ..> CurrencyOldWSRepository : provides RestClient
    JpaRepositoryConfig ..> BalanceJpaRepository : registers
    JpaRepositoryConfig ..> CardJpaRepository : registers
    JpaRepositoryConfig ..> BenefitJpaRepository : registers

    TransactionalUseCaseAspect ..> CardApi : TransactionalUseCase intercepts
    GlobalControllAdvice ..> BadRequestException : handles
    GlobalControllAdvice ..> UnprocessableEntityException : handles
    GlobalControllAdvice ..> BadGatewayException : handles
    GlobalControllAdvice ..> InternalServerErrorException : handles
```

---

## Leyenda de Capas

| Namespace (Mermaid) | Responsabilidad |
|---|---|
| `PresentationLayer` | Controllers, Delegates, Schemas (Request/Response) |
| `ApplicationLayer` | Services, DTOs |
| `UseCases` | Lógica de negocio, Factories de casos de uso |
| `Domain` | Entidades JPA/NoSQL, Enums, Value Objects, Excepciones |
| `Ports` | Interfaces de salida (Output Ports) |
| `Adapters` | Implementaciones de los puertos (Adapters outbound) |
| `Infrastructure` | Repositorios JPA/NoSQL, Config, AOP, Mappers, External Systems |
| `SpringFramework` | Interfaces externas de Spring / Azure Data |

---

## Perfiles de Spring

### Perfil `old`
- `ConsumptionMongoRepositoryAdapter` activo
- `PaymentMongoRepositoryAdapter` activo
- `CurrencyOldWSRepository` activo
- **MongoDB** como almacén NoSQL

### Perfil `new`
- `ConsumptionCosmosRepositoryAdapter` activo
- `PaymentCosmosRepositoryAdapter` activo
- `CurrencyNewWSRepository` activo
- **Azure Cosmos DB** como almacén NoSQL
