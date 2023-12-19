# PROYECTO UTILIDADES
Este repositorio tiene un conjunto de clases que pueden ser usadas en diferentes proyectos.

De esta manera, se evita la duplicación de clases y métodos.\
La idea principal de este proyecto es que se use como libreria y probar las caracteristicas de java 15.

A su vez, se realizaran test para poder tener un ciclo completo de trabajo. 

```mermaid
graph LR
A[Code] ----> B[Test]
B ----> C[Deploy]
````

## Caracteristicas

#### Lenguaje de programación: JAVA 15 SE
#### Test: Junit 5
#### Base de datos: MySQL
#### Gestor de proyecto: Maven
#### Control de versionado: Git
#### Repositorio: GitHub

## Dependencias
- Mysql
- Junit
- Assertj-core
- Commons-codec
- Eclipselink

## Autor

[@FerA01](https://www.github.com/FerA01)

## Clases involucradas
Como base, se tendra una clase abstracta para clases de reglas de negocio (Business), servicios (Dao) y entidades (Entities).
Habra una clase util en el cual tendra diferentes métodos puedan ser usados por varias clases.

Y también habra una carpeta que tenga diferentes excepciones creadas según las necesidades.

### Business

### Dao
* AbstractDao (src/main/java/org/core/utilidades/dao/AbstractDao.java)
* PersonaDao
* OrganizacionDao
* UsuarioPersonaDao
* UsuarioOrganizacionDao
### Entity

* AbstractEntity
* Organizacion
* Persona
* UsuarioOrganizacion
* UsuarioPersona
  #### Interface
  * Titular
  * Usuario

### Util

## Test
Se testearan las entidades involucradas tratando de obtener un alto porcentaje de cobertura (mayor a 80%).

### org.core.utilidades.util
* encoder.Encoder: Cobertura 100%
* exception.NumeroFueraDeRangoException: Cobertura 100%
* EnRango: Cobertura 85%

### org.core.utilidades.entity
* Organizacion: Cobertura 100% (*)
* Persona: Cobertura 100% (*)
* UsuarioPersona: Cobertura 100% (*)
* UsuarioOrganizacion: Cobertura 100% (*)
* AbstractEntity: Cobertura 100% (*)

(*) Al hacer el test con cobertura se toman los getters, setters y constructores lo que reduce el porcentaje de cobertura. Por lo que omito ese porcentaje. 

### org.core.utilidades.dao
* AbstractDao
* OrganizacionDao
* PersonaDao: Cobertura 75% 
* UsuarioOrganizacionDao
* UsuarioPersonaDao

![Modelo Base de Datos](https://github.com/FerA01/Core/blob/7983f91b96354de14cd03480d55d41db4db27878/src/main/resources/static/modelo.png)



