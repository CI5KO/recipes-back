# Recipes Back - API REST

API REST para gestión de recetas, ingredientes y etiquetas construida con Spring Boot y PostgreSQL.

## 📁 Estructura del Proyecto

```
recipes-back/
├── src/main/java/com/recipesback/
│   ├── Application.java              # Clase principal Spring Boot
│   ├── controller/                   # Controladores REST
│   │   ├── Recipes.java             # CRUD de recetas
│   │   ├── IngredientController.java # CRUD de ingredientes
│   │   └── TagController.java       # CRUD de etiquetas
│   ├── service/                      # Lógica de negocio
│   │   ├── RecipeService.java
│   │   ├── IngredientService.java
│   │   └── TagService.java
│   ├── repository/                   # Acceso a datos (JPA)
│   │   ├── RecipeRepository.java
│   │   ├── IngredientRepository.java
│   │   └── TagRepository.java
│   └── model/                        # Entidades JPA
│       ├── Recipe.java              # Receta
│       ├── Ingredient.java          # Ingrediente
│       ├── RecipeIngredient.java    # Relación receta-ingrediente
│       └── Tag.java                 # Etiqueta
└── src/main/resources/
    └── application.properties        # Configuración Spring Boot
```

## 🏗️ Arquitectura

**Patrón MVC en capas:**
- **Controller**: Endpoints REST (HTTP)
- **Service**: Lógica de negocio
- **Repository**: Persistencia con JPA/Hibernate
- **Model**: Entidades de base de datos

## 📊 Modelo de Datos

### Entidades Principales

**Recipe** (Receta)
- id, name, description, instructions
- preparationTime (minutos), difficulty, servings
- Relaciones: OneToMany con RecipeIngredient, ManyToMany con Tag

**Ingredient** (Ingrediente)
- id, name, unitOfMeasure, pricePerUnit

**RecipeIngredient** (Tabla intermedia)
- id, recipe, ingredient, quantity, notes

**Tag** (Etiqueta)
- id, name, type

## 🔌 API Endpoints

### Recetas
- `GET /recipes` - Listar todas
- `GET /recipes/{id}` - Obtener por ID
- `POST /recipes` - Crear
- `PUT /recipes/{id}` - Actualizar
- `DELETE /recipes/{id}` - Eliminar

### Ingredientes
- `GET /ingredients` - Listar todos
- `GET /ingredients/{id}` - Obtener por ID
- `POST /ingredients` - Crear
- `PUT /ingredients/{id}` - Actualizar
- `DELETE /ingredients/{id}` - Eliminar

### Etiquetas
- `GET /tags` - Listar todas
- `GET /tags/{id}` - Obtener por ID
- `POST /tags` - Crear
- `PUT /tags/{id}` - Actualizar
- `DELETE /tags/{id}` - Eliminar

## 🚀 Levantar con Docker

### Prerequisitos
- Docker
- Docker Compose

### 1. Configurar variables de entorno

Edita el archivo `.env`:
```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=recipes_db
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

### 2. Levantar PostgreSQL

```bash
docker run -d \
  --name postgres-recipes \
  -e POSTGRES_DB=recipes_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:15
```

### 3. Levantar la aplicación

**Modo desarrollo (con hot-reload):**
```bash
docker-compose up
```

**Modo producción:**
Descomenta la sección de producción en `docker-compose.yml` y ejecuta:
```bash
docker-compose up --build
```

### 4. Acceder a la API

La API estará disponible en: `http://localhost:8080`

Ejemplo:
```bash
curl http://localhost:8080/recipes
```

## 🛠️ Desarrollo Local (sin Docker)

### Prerequisitos
- Java 17+
- Maven 3.6+
- PostgreSQL 15+

### Pasos

1. **Crear base de datos:**
```sql
CREATE DATABASE recipes_db;
```

2. **Configurar .env** (ver paso 1 de Docker)

3. **Ejecutar:**
```bash
mvn spring-boot:run
```

## 🗄️ Base de Datos

Hibernate crea automáticamente las tablas al iniciar (`spring.jpa.hibernate.ddl-auto=update`).

**Tablas generadas:**
- `recipes`
- `ingredients`
- `recipe_ingredients`
- `tags`
- `recipe_tags`

## 📦 Tecnologías

- **Spring Boot 3.2.0** - Framework
- **Spring Data JPA** - ORM
- **Hibernate** - Implementación JPA
- **PostgreSQL** - Base de datos
- **Maven** - Gestión de dependencias
- **Docker** - Contenedorización
