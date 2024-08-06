# sistema-banco-digital
Sistema de transfências entre usuário e mercador com uma gama de features e microserviço
&nbsp;

### Lógica
Será cadastrado dois usuários, um usuário comum que realizará transferências com o mercador ou "merchant", não será possivel fazer com dois usuários comuns, o usuário pode decidir de como será sua conta pelo cadastramento POST, e caso queira retornar as transações, será necessario efetuar um login e sairá um e-mail de confirmação.

### Mensageria e Microserviços
Utilizei o framework RabbitMQ

#### Notas
Utilizem este link para gerar urls para quem deseja clonar: https://designer.mocky.io/
Utilizei o cloudAMQP para o rabbitMQ, mas para quem possui o Erlang e o Rabbit, no meu outro projeto microservice de email possui a conexão correta.

## Stacks:
- Java
- Springboot
- Docker
- RabbitMQ
- MySQL
- H2
