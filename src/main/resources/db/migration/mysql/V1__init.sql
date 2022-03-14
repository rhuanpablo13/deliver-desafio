-- criando o database de desenvolvimento (que tbm vai servir de teste)
CREATE DATABASE IF NOT EXISTS deliver_dev; 

-- criando o database de produção
CREATE DATABASE IF NOT EXISTS deliver_prod;

SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;