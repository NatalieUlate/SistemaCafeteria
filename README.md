# Sistema de Punto de Venta (POS) para Cafetería

Este proyecto es un sistema de escritorio para la gestión de un punto de venta , desarrollado en Java con una interfaz gráfica de usuario (GUI) Swing y respaldado por una base de datos PostgreSQL. La aplicación está diseñada para manejar las operaciones diarias de una cafetería, incluyendo la gestión de productos, el procesamiento de ventas y la autenticación de usuarios.

##  Características Principales

* **Gestión de Usuarios**: Creación, actualización y listado de usuarios del sistema con diferentes roles.
* **Autenticación Segura**: Inicio de sesión de usuarios con validación de credenciales y almacenamiento seguro de contraseñas mediante hashing.
* **Gestión de Inventario**: Funcionalidad CRUD (Crear, Leer, Actualizar, Eliminar) completa para productos y categorías. La eliminación de productos es un "borrado suave" (soft-delete) para mantener la integridad de los registros históricos.
* **Procesamiento de Ventas**: Registro de transacciones de venta de manera atómica y segura, utilizando transacciones de base de datos para garantizar la consistencia de los datos.
* **Cálculos Automáticos**: Cálculo automático de subtotales, impuestos (IVA, IVI) y totales en cada venta.
* **Registro de Actividad (Logging)**: Un sistema de logging robusto que guarda eventos importantes y errores directamente en la base de datos para facilitar la depuración y auditoría.
* **Validación de Entradas**: Validación de todos los datos ingresados por el usuario para prevenir errores y garantizar la calidad de la información.

##  Arquitectura del Software

El sistema está construido siguiendo una arquitectura en capas bien definida, lo que facilita su mantenimiento, escalabilidad y testeo.


1.  **Capa de Presentación (GUI)**
    * Construida con **Java Swing**, proporciona la interfaz con la que el usuario interactúa. Se encarga de mostrar los datos y capturar las entradas del usuario.

2.  **Capa de Servicios (Lógica de Negocio)**
    * `ServicioAutenticacion`: Orquesta la lógica para el login, validando credenciales contra el repositorio.
    * `ServicioProducto`: Maneja la lógica de negocio para la gestión de productos.
    * `ServicioVenta`: Coordina el proceso de guardado de una venta, asegurando que todos los pasos se ejecuten correctamente.
    * Esta capa actúa como un puente entre la GUI y la capa de acceso a datos.

3.  **Capa de Infraestructura (Acceso a Datos)**
    * Implementa el **Patrón Repositorio** para abstraer la lógica de la base de datos.
    * `ProductoRepositorio`, `VentaRepositorio`, `UsuarioRepositorio`, `LogRepositorio`: Contienen las consultas SQL (usando `PreparedStatement` para prevenir inyección SQL) y la lógica para interactuar con la base de datos PostgreSQL.
    * `ConexionBD`: Centraliza y gestiona la conexión a la base de datos.

4.  **Capa de Dominio (Modelos)**
    * Contiene los objetos POJO (Plain Old Java Objects) que modelan las entidades del negocio: `Producto`, `Categoria`, `Venta`, `DetalleVenta`, `Usuario`.

5.  **Paquete de Utilidades**
    * Clases de apoyo utilizadas en todo el sistema.
    * `CodificadorContrasena`: Encapsula la lógica para crear y verificar hashes de contraseñas.
    * `Formateador`: Proporciona métodos para dar formato a números, fechas, etc.
    * `ValidadorEntrada`: Centraliza las reglas de validación para los datos de entrada.

##  Tecnologías Utilizadas

* **Lenguaje**: Java
* **Interfaz Gráfica**: Java Swing
* **Base de Datos**: PostgreSQL
* **Conectividad**: JDBC (Java Database Connectivity)
* **Patrones de Diseño**:
    * Arquitectura en Capas
    * Patrón Repositorio
    * Singleton (en `ConexionBD`)

##  Base de Datos

El sistema utiliza las siguientes tablas principales en una base de datos PostgreSQL llamada `cafeteria`:

* `usuarios`: Almacena las credenciales y roles de los usuarios.
* `categorias`: Define las categorías de los productos.
* `productos`: Contiene la información de todos los productos a la venta.
* `ventas`: La cabecera de cada transacción, con los totales.
* `detalle_ventas`: Las líneas de cada venta, vinculando productos y cantidades.
* `logs`: Un registro de todos los sucesos y errores del sistema.

##  Cómo Empezar

Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local.

### Prerrequisitos

* Tener instalado un JDK (Java Development Kit).
* Tener instalado y en ejecución un servidor de base de datos PostgreSQL.
* Un IDE de Java como NetBeans, IntelliJ IDEA o Eclipse.

### Instalación

1.  **Clona el repositorio:**
    ```sh
    git clone https://github.com/NatalieUlate/SistemaCafeteria.git
    ```

2.  **Configura la base de datos:**
    * Crea una nueva base de datos en PostgreSQL llamada `cafeteria`.
    * Ejecuta el script SQL (que deberá crear) para generar las tablas necesarias (`usuarios`, `productos`, etc.).

3.  **Configura la conexión:**
    * Abre el archivo `src/infraestructura/ConexionBD.java`.
    * Modifica las credenciales de la base de datos (URL, usuario y contraseña) para que coincidan con tu configuración local.

4.  **Ejecuta la aplicación:**
    * Busca la clase principal que contiene el método `main` (probablemente en la capa de GUI, por ejemplo, `LoginFrame.java`).
    * Ejecute esa clase desde tu IDE.
    * Puede usar para el login los credenciales de nulate y contrasena 123456