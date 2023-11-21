# Homework
Proyecto modulo 3 Diplomado en Desarrollo, no pense muy bien el nombre y para cuando queria cambiarlo ya habia avanzado demasiado.

# Parametros de conexion a la base de datos
    url: jdbc:postgresql://localhost:5432/qa_homework_db
    username: postgres
    password: postgres
 El ambiente QA ya viene con unos datos insertados en todas las tablas

# Bases de datos adicionales 
- dev_homework_db
- qa_homework_db
- Produccion y staging no cuentan con flyAway

# Requerimientos adicionales realizados
- Implementacion de manejo de Excepciones (En User, Rol y UserDetail)
- Una API que liste por un rol determinado todos los usuarios que tienen ese Rol específico (GetUsersByRole y GetRolesByUser)
