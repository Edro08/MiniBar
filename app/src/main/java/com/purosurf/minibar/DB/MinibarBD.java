package com.purosurf.minibar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MinibarBD extends SQLiteOpenHelper {

    public MinibarBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String consultaSql;

        consultaSql = "CREATE TABLE ESTADO " +
                "([IDESTADO] INTEGER PRIMARY KEY AUTOINCREMENT," +
                "[ESTADONOMBRE][NVARCHAR] (10) NOT NULL)";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE CATEGORIA" +
                "([IDCATEGORIA] INTEGER PRIMARY KEY AUTOINCREMENT," +
                "[NOMBRECATEGORIA][NVARCHAR] (25) NOT NULL," +
                "[IDESTADO][INT] NOT NULL," +
                "CONSTRAINT FK_IDESTADO_CAT FOREIGN KEY (IDESTADO) REFERENCES ESTADO(IDESTADO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE PERSONA" +
                "([IDPERSONA] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[NOMBRE][NVARCHAR](25) NOT NULL," +
                "[APELLIDO][NVARCHAR](25) NOT NULL," +
                "[CORREOELECTRONICO][NVARCHAR](250) NOT NULL," +
                "[PREGUNTASEGURIDAD][NVARCHAR](100) NOT NULL," +
                "[REPUESTASEGURIDAD][NVARCHAR](100) NOT NULL)";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE USUARIO " +
                "([IDUSUARIO] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[USUARIO][NVARCHAR](25) UNIQUE NOT NULL," +
                "[CONTRASEÑA][NVARCHAR](50) NOT NULL," +
                "[IDESTADO][INT] NOT NULL," +
                "[IDPERSONA][INT] NOT NULL," +
                "CONSTRAINT FK_IDESTADO_USUARIO FOREIGN KEY (IDESTADO) REFERENCES ESTADO(IDESTADO)," +
                "CONSTRAINT FK_IDPERSONA_USUARIO FOREIGN KEY (IDPERSONA) REFERENCES PERSONA(IDPERSONA))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE HABITACION" +
                "([IDHABITACION] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[NOMBREHABITACION][NVARCHAR](25) NOT NULL," +
                "[IDESTADO][INT] NOT NULL," +
                "CONSTRAINT FK_IDESTADO_HABITACION FOREIGN KEY (IDESTADO) REFERENCES ESTADO(IDESTADO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE PRODUCTO " +
                "([IDPRODUCTO] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[PRODUCTONOMBRE][NVARCHAR](35) NOT NULL," +
                "[IDCATEGORIA][INT] NOT NULL," +
                "[PRECIOUNITARIO][FLOAT] NOT NULL," +
                "[IDESTADO][INT] NOT NULL," +
                "[IMAGENURL][NVARCHAR](250) NOT NULL," +
                "CONSTRAINT FK_IDCATEGORIA_PRODUCTO FOREIGN KEY (IDCATEGORIA) REFERENCES CATEGORIA(IDCATEGORIA)," +
                "CONSTRAINT FK_IDESTADO_PRODUCTO FOREIGN KEY (IDESTADO) REFERENCES ESTADO(IDESTADO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE INVENTARIOHABITACION" +
                "([IDINVENTARIOHABITACION] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDHABITACION][INT] NOT NULL," +
                "[IDPRODUCTO][INT] NOT NULL," +
                "[EXISTENCIAS][INT] NOT NULL," +
                "[CANTIDADMINIMA][INT] NOT NULL," +
                "CONSTRAINT FK_IDHABITACION_INVENTARIOHABITACION FOREIGN KEY (IDHABITACION) REFERENCES HABITACION(IDHABITACION)," +
                "CONSTRAINT FK_IDPRODUCTO_INVENTARIOHABITACION FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE INVENTARIO" +
                "([IDINVENTARIO] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDPRODUCTO][INT] NOT NULL," +
                "[CANTIDADMINIMA][INT] NOT NULL," +
                "[CANTIDADMAXIMA][INT] NOT NULL," +
                "[EXISTENCIAS][INT] NOT NULL," +
                "CONSTRAINT FK_IDPRODUCTO_INVENTARIO FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE CONSUMO" +
                "([IDCONSUMO] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDUSUARIO][INT] NOT NULL," +
                "[IDHABITACION][INT] NOT NULL," +
                "[FECHA][DATE] NOT NULL," +
                "[TOTAL][FLOAT] NOT NULL," +
                "CONSTRAINT FK_IDUSUARIO_CONSUMO FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO)," +
                "CONSTRAINT FK_IDHABITACION_CONSUMO FOREIGN KEY (IDHABITACION) REFERENCES HABITACION(IDHABITACION))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE DETALLECONSUMO" +
                "([IDDETALLECONSUMO] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDCONSUMO][INT] NOT NULL," +
                "[IDPRODUCTO][INT] NOT NULL," +
                "[CANTIDAD][INT] NOT NULL," +
                "[SUBTOTAL][FLOAT] NOT NULL," +
                "CONSTRAINT FK_IDCONSUMO_DETALLECONSUMO FOREIGN KEY (IDCONSUMO) REFERENCES CONSUMO(IDCONSUMO)," +
                "CONSTRAINT FK_IDPRODUCTO_DETALLECONSUMO FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE ENTRADA" +
                "([IDENTRADA] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDUSUARIO][INT] NOT NULL," +
                "[IDPRODUCTO][INT] NOT NULL," +
                "[DESCRIPCION][NVARCHAR](100) NOT NULL," +
                "[FECHA][DATE] NOT NULL," +
                "[CANTIDAD][INT] NOT NULL," +
                "[PRECIO][FLOAT] NOT NULL," +
                "[TOTAL][FLOAT] NOT NULL," +
                "CONSTRAINT FK_ISUARIO_ENTRADA FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO)," +
                "CONSTRAINT FK_IDPRODUCTO_ENTRADA FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO))";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "CREATE TABLE SALIDA" +
                "([IDSALIDA] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[IDUSUARIO][INT] NOT NULL," +
                "[IDPRODUCTO][INT] NOT NULL," +
                "[DESCRIPCION][NVARCHAR](100) NOT NULL," +
                "[FECHA][DATE] NOT NULL," +
                "[CANTIDAD][INT] NOT NULL," +
                "[PRECIO][FLOAT] NOT NULL," +
                "[TOTAL][FLOAT] NOT NULL," +
                "CONSTRAINT FK_IDUSUARIO_SALIDA FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO)," +
                "CONSTRAINT FK_IDPRODUCTO_SALIDA FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO))";
        sqLiteDatabase.execSQL(consultaSql);

        Datos(sqLiteDatabase);
        Asistencia(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Datos(SQLiteDatabase sqLiteDatabase)
    {
        String consultaSql;

        //Datos para tabla Estado
        consultaSql = "INSERT INTO ESTADO(ESTADONOMBRE) VALUES('Activo')";
        sqLiteDatabase.execSQL(consultaSql);
        consultaSql = "INSERT INTO ESTADO(ESTADONOMBRE) VALUES('Inactivo')";
        sqLiteDatabase.execSQL(consultaSql);

        //Datos para Usuario Admin
        consultaSql = "INSERT INTO PERSONA(NOMBRE,APELLIDO,CORREOELECTRONICO,PREGUNTASEGURIDAD,REPUESTASEGURIDAD) " +
                "VALUES('Administrador', 'Del Sistema','Hotelpurosurf@gmail.com','Año que inicio operaciones el hotel','2016')";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "INSERT INTO USUARIO(USUARIO,CONTRASEÑA,IDESTADO,IDPERSONA) " +
                "VALUES('Admin','Admin',1,1)";
        sqLiteDatabase.execSQL(consultaSql);

        //Datos para Usuario Empleado
        consultaSql = "INSERT INTO PERSONA(NOMBRE,APELLIDO,CORREOELECTRONICO,PREGUNTASEGURIDAD,REPUESTASEGURIDAD) " +
                "VALUES('Eduardo', 'Garcia','Edrogarcia08@gmail.com','Mi Anime favorito es','MKR')";
        sqLiteDatabase.execSQL(consultaSql);

        consultaSql = "INSERT INTO USUARIO(USUARIO,CONTRASEÑA,IDESTADO,IDPERSONA) " +
                "VALUES('Edro08','Admin',1,2)";
        sqLiteDatabase.execSQL(consultaSql);
    }

    public void Asistencia(SQLiteDatabase sqLiteDatabase)
    {
        //CATEGORIAS DE PRODUCTOS
        String[] sqlInsertCat = {
                "INSERT INTO CATEGORIA(NOMBRECATEGORIA,IDESTADO) VALUES ('Frutos Secos', 1);",
                "INSERT INTO CATEGORIA(NOMBRECATEGORIA,IDESTADO) VALUES ('Bebidas Frias', 1);"
        };
        for(String insert : sqlInsertCat){
            sqLiteDatabase.execSQL(insert);
        }

        //------------------------------------------------------------------------------------

        String[] sqlInsertPrd = {
                //PRODUCTOS CATEGORIA FRUTOS FRESCOS
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Nueces Nature Valley',1, 1.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Yuca',1, 1.75, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Platano',1, 1.75, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Camote',1, 2.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Granuts - Nueces mixtas',1, 1.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Papas Pringles',1, 2.25, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pistachos',1, 2.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Protehina Nature Valley',1, 1.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Garbanzos Horneados c/Sal',1, 2.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Tasu Fruta Deshidratada',1, 2.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pasas Cubiertos de Chocolate',1, 3.25, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Arandanos Cubiertos de Chocolate',1, 3.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Mini Barra de Chocolate',1, 3.00, 1, 'URL-');",
                //PRODUCTOS CATEGORIA BEBIDAS FRIAS
                //SODAS
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral Kinkey',2, 2.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Kirkland jugo Organico',2, 1.50, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral San Pellegrino',2, 4.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Colca Cola',2, 2.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Electrolit',2, 4.00, 1, 'URL-');",
                //alcohocilas
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Chardonnay Vino Blanco',2, 10.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Merlot Vino Tinto ',2, 10.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza Heineken',2, 4.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Squirrel Eye Brewing Cerveza IPA',2, 5.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Squirrel Eye Brewing Cerveza APA',2, 5.30, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Lager',2, 2.35, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Pale Ale',2, 5.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chandon Vino Blanco Espumante',2, 10.00, 1, 'URL-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Fitzer aAUGA pBREVA',2, 3.35, 1, 'URL-');",

        };
        for(String insert : sqlInsertPrd){
            sqLiteDatabase.execSQL(insert);
        }
    }
}
