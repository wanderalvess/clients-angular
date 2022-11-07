Servi�o consulta e cadastro de Clientes
======


### Servi�os Dispon�veis:

### 1) ***Consulta de todos clientes cadastrados***
   - Nessa consulta, basta apenas fazer a requisi��o da url.


- ***M�todo:*** GET


- ***Url:*** `http://localhost:8080/api/clients`


- ***Exemplo:*** `http://localhost:8080/api/clients`


- ***Retorno:*** 
				{
					"name": "Jo�o",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 1
				}


### 2) ***Consulta de clientes cadastrados por id***
   - Nessa consulta, basta apenas passar o id do cliente na url.


- ***M�todo:*** GET


- ***Url:*** `http://localhost:8080/api/clients/{id}`


- ***Exemplo:*** `http://localhost:8080/api/clients/1`


- ***Retorno:*** 
				{
					"name": "Jo�o",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 1
				}


### 3) ***Cadastro de clientes***
   - Nesse servi�o, � preciso informar todos os dados do cliente no corpo da requisi��o para o cadastro. � utilizado o m�todo POST.


- ***M�todo:*** POST


- ***Url:*** `http://localhost:8080/api/clients`

- ***Par�metros:*** 

  - **name:** Nome do Contribuinte
  - **document:** O documento pode ser informado o CNPJ ou CPF do cliente
  - **address:** Nesse parametro � informado o endere�o do cliente
  - **latitude:** � informado a latitude do endere�o do cliente
  - **longitude:** � informado a longitude do endere�o do cliente


- ***Exemplo:*** 
				{
					"name": "Maria",
					"document": "22.222.222/0001-22",
					"address": "Av. 122",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458"
				}


- ***Retorno:*** 
				{
					"name": "Maria",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 3
				}

### 4) ***Atualiza��o de clientes***
   - Nesse servi�o, ser� feito a atualiza��o dos dados do cliente, � preciso informar as altera��es do cliente no corpo da requisi��o para o cadastro.


- ***M�todo:*** PUT


- ***Url:*** `http://localhost:8080/api/clients/{id}`

- ***Par�metros:*** 

  - **name:** Nome do Contribuinte
  - **document:** O documento pode ser informado o CNPJ ou CPF do cliente
  - **address:** Nesse parametro � informado o endere�o do cliente
  - **latitude:** � informado a latitude do endere�o do cliente
  - **longitude:** � informado a longitude do endere�o do cliente


- ***Exemplo:*** 
				{
					"name": "Pedro",
					"document": "33.333.333/0001-33",
					"address": "Av. 133",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458"
				}


- ***Retorno:*** 
				{
					"name": "Pedro",
					"document": "33.333.333/0001-33",
					"address": "Av. 133",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id":3
				}

### 5) ***Exclus�o de clientes***
   - Nessa consulta, basta apenas passar o id do cliente na url e ser� feito a exclus�o do cliente.


- ***M�todo:*** DELETE


- ***Url:*** `http://localhost:8080/api/clients/{id}`


- ***Exemplo:*** `http://localhost:8080/api/clients/1`


- ***Retorno:*** 




# docker build -t wanderalvess/clients-front:1.0.0 .
# docker run -p 8081:8081 wanderalvess/clients-front:1.0.0

# docker build -t wanderalvess/clients-back:1.0.0 .
# docker run -p 8080:8080 wanderalvess/clients-back:1.0.0

# docker run -it --name=Frontend  --rm -p 8081:8081 wanderalvess/clients-front:1.0.0

# Baixar imagem docker:
# docker run wanderalvess/clients-back
# docker run wanderalvess/clients-front

# Pode ser usado o comando abaixo, para iniciar todos ao mesmo tempo
# docker-compose up
