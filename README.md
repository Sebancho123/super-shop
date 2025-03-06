# 🛒 Proyecto Supermercado

¡Hola! Bienvenido al proyecto de un supermercado. Aquí tienes las indicaciones para probarlo en tu máquina local.

## 📥 Descarga el código fuente

Clona este repositorio o descárgalo como un archivo ZIP y extrae los archivos en tu máquina.

## 🛠 Configuración previa

### 1️⃣ Base de Datos

Este proyecto usa **PostgreSQL**, por lo que debes asegurarte de tenerlo instalado y configurado. Luego, crea las bases de datos necesarias.

### 2️⃣ Archivo de configuración

Debes crear un archivo con extensión `.env` en la raíz de la carpeta donde estan todos lo servicioos. En este archivo, agrega la información de conexión a las bases de datos, como el usuario, la contraseña y los nombres de las bases de datos. Ejemplo:

```
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
DB_URL_PRODUCT=tu_base_de_datos
```

## 🚀 Ejecución del proyecto

1. **Asegúrate de tener Docker Desktop instalado y en ejecución**.
2. Abre una terminal (CMD o PowerShell) en el directorio donde descargaste el proyecto.
3. Ejecuta el siguiente comando:

   ```sh
   docker-compose --env-file tuarchivo.env up --build
   ```
   
   📌 *Reemplaza `tuarchivo.env` con el nombre del archivo .env que creaste.*

## 🔗 Acceder a la documentación de las APIs

Una vez que el proyecto esté en ejecución, puedes acceder a la documentación Swagger en la siguiente URL:

```
http://localhost:<puerto>/doc/swagger-ui.html
```

📌 *El puerto de cada servicio está definido en el archivo `.yml` del proyecto.*

## 🎉 ¡Listo!

Ahora puedes probar las APIs y explorar el proyecto. ¡Mucha suerte! 🚀

