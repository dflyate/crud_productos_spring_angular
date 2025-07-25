# Crud de productos

Aplicación web full stack para manejo de productos con Spring y Angular

---

## Tecnologías utilizadas

- **Frontend:** Angular 12.2.0, Node 20.11.1
- **Backend:** Spring 3.0.6, JDK 17
- **Base de datos:** MySQL 8.0

---

## Funcionalidades principales

- **Productos:** Crear, consultar, actualizar y eliminar productos

---


## 📸 Capturas de pantalla

<img width="921" height="248" alt="image" src="https://github.com/user-attachments/assets/ec6a5fbd-de35-447c-9a91-bf452bc144b5" />

<img width="921" height="347" alt="image" src="https://github.com/user-attachments/assets/73a71409-7e53-472b-a483-9d18b9ae8502" />

<img width="921" height="440" alt="image" src="https://github.com/user-attachments/assets/4da569cb-3335-4a77-b9fa-cdf62ac7a8cd" />

<img width="921" height="382" alt="image" src="https://github.com/user-attachments/assets/f9cbe7c7-ad1f-4470-9e39-19ff046e47eb" />



## Instalación y ejecución local

### Descargar el proyecto

Clonar el repositorio:

```bash
git clone https://github.com/dflyate/crud_productos_spring_angular.git
```

### PARA EL FRONTEND

- Ingresar a la carpeta `producto_front/productos`
- Abrir una consola de comandos y ejecutar:

```bash
npm install
```

- Ejecutar el servidor de desarrollo:

```bash
npm start
```

- Abrir la ruta generada desde un navegador: `http://localhost:4200/productos`

### BASE DE DATOS MYSQL


- Abre tu cliente de MySQL (puede ser MySQL Workbench, DBeaver, o línea de comandos).
-  Ejecuta el siguiente comando SQL para crear la base de datos:

```sql
CREATE DATABASE empresa_productos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```


### PARA EL BACKEND 

- Ingresar a la carpeta `producto_backend`
- Abrir el archivo application.properties desde la ubicación: src\main\resources
- cambiar los parametros username y password por los que asignó a su base de datos
- Abrir otra consola de comandos y ejecutar:

```bash
mvn spring-boot:run
```
