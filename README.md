# challenge de reservas
 
En este presente proyecto se pudo desarrollar una aplicacion de escritorio que permita a los usuarios de un hotel ficticio adicionar reservaciones y huespedes que se hospeden en dicho hotel. Este proyecto fue desarrollado gracias a los conocimientos aduquiridos gracias a ONE.

## :hammer:Funcionalidades del proyecto

- Inicio de sesión de usuarios.
- Panel de bienvenida y acceso a los distintos formularios.
- Formularios de adición de reservaciones y huespedes.

## 📁 Acceso al proyecto

Tienes acceso al código fuente del proyecto, como no cuentas con la base de datos utilizada en el proyecto no puedo comparirte un jar para ejecutar, pero tienes acceso completo a descarga y revisión del código fuente que se utiliza.

## 🛠️ Aspecto y uso de la apliación

Al iniciar la aplicación desde el primer formulario te encontrarás la siguiente pantalla:

![image](https://user-images.githubusercontent.com/119614792/235818858-846eafbb-59eb-4d68-86b0-644a91008f1c.png)

Al presionar el botón de la persona podrás ingresar al programa mediante un usuario y contraseña. Se abrirá una página de inicio de sesión de la siguiente manera:

![image](https://user-images.githubusercontent.com/119614792/235818948-0852ecad-db42-4fb4-bc11-96c66fb4fd96.png)
El proyecto utiliza una tabla de una base de datos para obtener los datos de inicio de sesión, es decir, almacena en la base de datos los datos de usuario y contraseña.

Al ingresar te encontrarás con un menú en el que podrás elegir si quieres buscar o realizar un nuevo registro.

![image](https://user-images.githubusercontent.com/119614792/235819320-09000adb-a573-4e51-a09d-094382214ddd.png)

Para ingresar una reservación ingresas fecha de entrada y salida y se automatiza el proceso de precios diarios:

![image](https://user-images.githubusercontent.com/119614792/235819437-9b929e46-a205-4145-9eb8-42a1282de996.png)

al dar al botón siguiente se crea automáticamente un id de la reservación y este se pasa al siguiente formulario y se almacena dentro de un textbox, tal como se muestra en la imágen:

![image](https://user-images.githubusercontent.com/119614792/235820071-8aa41803-ef86-4271-91fe-ddb1057c1784.png)

Al realizar un registro exitosamente se lanzara el siguiente mensaje:

![image](https://user-images.githubusercontent.com/119614792/235819895-fc7de28b-6cbe-40e7-9d0c-93b56fb5b5d8.png)

Si se omite algún cam po en cualquier formulario saldra una ventana emergente como la siguiente:

![image](https://user-images.githubusercontent.com/119614792/235819766-7f541180-4740-43cf-a19e-5690365e34d0.png)

Al ingresar al sistema de busquedas se cargarán automáticamente todos los registros relacionados con los huespedes o las reservaciones, dicha separación se encuentra en las pestañas sobre la tabla. Se puede realizar una busqueda tanto de id o del apellido de los huespedes. No es necesario seleccionar el tipo de busqueda ya que el id de los registros es numerico, por lo que si se ingresa un numero se buscará automáticamente por id, y si se ingresan letras se buscará por apellido

![image](https://user-images.githubusercontent.com/119614792/235820328-48717be9-885f-4589-86aa-ba4ca7761573.png)

## ✅ Tecnologías utilizadas
- Java 11
- Eclipse 
- Biblioteca JCalendar
- MySql
- Plugin WindowBuilder
