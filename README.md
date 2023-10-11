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
### Entity

* AbstractEntity 
* Organizacion
* Persona
* UsuarioOrganizacion
* UsuarioPersona

### Util

![Modelo Base de Datos](C:%5CUsers%5CUsuario%5CDesktop%5Cmodelo.png)



