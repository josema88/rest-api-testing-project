# Proyecto para pruebas de API's Rest con Java y TestNG
## Pasos para configurar proyecto

1. Crear un nuevo proyecto Java
2. Agregar Modulo Maven

3. Agregar Framework para automatizar pruebas TestNG como plugin de TestNG

4. Ir a la carpeta src/test/java y agregar nuevo paquete que contendrá las pruebas.

5. Crear Nueva Clase donde se agregara el código.

6. Agregar el decorador **@BeforeMethod** al constructor de la clase. 
7. Agregar la referencia: Add TestNG to ClassPath


8. Agregar en POM.xml los pasos para hacer build del proyecto con TestNG
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.19</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>TestNG.xml</suiteXmlFile>
                    </suiteXmlFiles>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

9. Agregar Configuración de TestNG para construir y correr las pruebas, ir a Edit Configurations y elegir All in Package.

10. Agregar Dependencia de RestAssured en el Pom.xml
```
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>3.0.2</version>
    <scope>test</scope>
</dependency>
```

11. Agregar imports estaticos a la clase donde se harán los test
```
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
```
