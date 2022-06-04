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
                "[IMAGENURL][NVARCHAR](2000) NOT NULL," +
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

        AsistenciaDatos(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AsistenciaDatos(SQLiteDatabase sqLiteDatabase)
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

        //  HABITACIONES
        String[] sqlInsertHab = {
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Deluxe H1',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Deluxe H2',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Deluxe H3',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Deluxe H4',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Normal 2 Camas F1',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Normal 2 Camas F2',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Normal 2 Camas F3',1);",
                "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Normal 2 Camas F4',1);",
        };
        for(String insert : sqlInsertHab){
            sqLiteDatabase.execSQL(insert);
        }


        //CATEGORIAS DE PRODUCTOS, PRODUCTOS, EXISTENCIAS
        String[] sqlInsertCat = {
                "INSERT INTO CATEGORIA(NOMBRECATEGORIA,IDESTADO) VALUES ('Frutos Secos', 1);",
                "INSERT INTO CATEGORIA(NOMBRECATEGORIA,IDESTADO) VALUES ('Bebidas Frias', 1);",
        };
        for(String insert : sqlInsertCat){
            sqLiteDatabase.execSQL(insert);
        }

        //------------------------------------------------------------------------------------

        String[] sqlInsertPrd = {
                //PRODUCTOS CATEGORIA FRUTOS FRESCOS
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Nueces Nature Valley',1, 1.50, 1, 'https://m.media-amazon.com/images/I/91-uBXgO5ML._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Yuca',1, 1.75, 1, 'https://www.thespruceeats.com/thmb/x_2mLZ7CEkSUmr7I0V5YJIwV3r8=/940x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Yucachips-285040_1920-59849c529abed500102b06d0.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Platano',1, 1.75, 1, 'https://www.recetin.com/wp-content/uploads/2013/09/chips_platano.jpg.webp');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Camote',1, 2.50, 1, 'https://www.tuhogar.com/content/dam/cp-sites/home-care/tu-hogar/es_mx/recetas/snacks-bebidas-y-postres/receta-de-chips-de-camote/receta-de-chips-de-camote-axion.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Granuts - Nueces mixtas',1, 1.50, 1, 'https://d1cft8rz0k7w99.cloudfront.net/n/d/3/b/9/d3b9507f5065a27a655f9347184ea02b06c2f3c0_Snacks_334892_01.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Papas Pringles',1, 2.25, 1, 'https://baymarsa.com/image/cache/catalog/Groceries%20products/Pringles/18508%20(Pringles)-500x500.png');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pistachos',1, 2.00, 1, 'https://m.media-amazon.com/images/I/71kjYpSZBsS._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Protehina Nature Valley',1, 1.50, 1, 'https://m.media-amazon.com/images/I/71sos+Cv31L._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Garbanzos Horneados c/Sal',1, 2.00, 1, 'https://static.wixstatic.com/media/a4cdba_6a83b77a79a74b2995d15001060f4198~mv2.jpg/v1/fill/w_3752,h_3920,al_c,q_85/a4cdba_6a83b77a79a74b2995d15001060f4198~mv2.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Tasu Fruta Deshidratada',1, 2.00, 1, 'https://tasusnacksmx.com/wp-content/uploads/empaque-platano-fresa.png.webp');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pasas Cubiertos de Chocolate',1, 3.25, 1, 'https://chocolatescostanzo.com/2014/images/categorias/chocolares_sin_envolver/10pasaConChocolate.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Arandanos Cubiertos de Chocolate',1, 3.50, 1, 'https://http2.mlstatic.com/D_NQ_NP_653183-MLM31999000712_082019-O.webp');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Mini Barra de Chocolate',1, 3.00, 1, 'https://m.media-amazon.com/images/I/61axB7x5yNL._AC_SY879_.jpg');",
                //PRODUCTOS CATEGORIA BEBIDAS FRIAS
                //SODAS
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral Kinkey',2, 2.00, 1, 'https://www.farmaciasannicolas.com/Multimedia/GetProductImage?productId=f73eb5f6-44c6-4b38-b7c6-f403b7eef141&multimediaId=edaa626a-4908-4780-a4c0-8493aa5547b3&imageSize=Original');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Kirkland jugo Organico',2, 1.50, 1, 'https://www.grocery.com/store/image/cache/catalog/kirkland-signature/costco-B01LTI04R0-600x600.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral San Pellegrino',2, 4.00, 1, 'https://asset4.farmsteadapp.com/uploads/item/image/668/thumb_400_san-pellegrino-sparkling-mineral-water-in-glass-750-ml.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Coca Cola',2, 2.00, 1, 'https://media.officedepot.com/images/f_auto,q_auto,e_sharpen,h_450/products/208206/208206_p/208206_p');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Electrolit',2, 4.00, 1, 'https://images.heb.com/is/image/HEBGrocery/003314502?fit=constrain,1&wid=800&hei=800&fmt=jpg&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0');",
                //alcohocilas
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Chardonnay Vino Blanco',2, 10.00, 1, 'https://www.vinoselkiosco.com/wp-content/uploads/2021/05/Vino-Frances-BG-Reserve-Chardonnay-750-mL.png');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Merlot Vino Tinto',2, 10.00, 1, 'https://images.vivino.com/thumbs/fJVG84WWRKqnbZ9Xpx4Dyg_pb_x600.png');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza Heineken',2, 4.00, 1, 'https://cdn.shopify.com/s/files/1/0297/6812/2412/products/Cervezaheinekenbotella330mlpza_540x.png?v=1627433719');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza IPA Squirrel Eye Brewing',2, 5.00, 1, 'https://untappd.akamaized.net/site/beer_logos/beer-4109604_74936_sm.jpeg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza APA Squirrel Eye Brewing',2, 5.30, 1, 'https://sebrewingco.com/wp-content/uploads/2018/01/HeyPA-300x300.jpeg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Lager',2, 2.35, 1, 'https://santocoraje.com/wp-content/uploads/2021/05/Copia-de-Copia-de-CLASSIC-PILS-SC.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Pale Ale',2, 5.00, 1, 'https://santocoraje.com/wp-content/uploads/2021/05/Copia-de-Copia-de-CLASSIC-PILS-SC.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chandon Vino Blanco Espumante',2, 10.00, 1, 'https://m.media-amazon.com/images/I/619dqlnqigL._AC_SX679_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Fitzer Agua Brava',2, 3.35, 1, 'https://cdn.shopify.com/s/files/1/0371/3538/4714/products/lsxoUYmCHb_455x455.jpg?v=1618330724');",

        };
        for(String insert : sqlInsertPrd){
            sqLiteDatabase.execSQL(insert);
        }

        String[] sqlInsrtInvt = {
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (1,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (2,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (3,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (4,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (5,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (6,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (7,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (8,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (9,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (10,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (11,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (12,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (13,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (14,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (15,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (16,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (17,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (18,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (19,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (20,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (21,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (22,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (23,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (24,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (25,20,200,10);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (26,20,200,20);",
                "INSERT INTO INVENTARIO (IDPRODUCTO, CANTIDADMINIMA, CANTIDADMAXIMA, EXISTENCIAS) VALUES (27,20,200,10);",

        };
        for (String insr : sqlInsrtInvt){
            sqLiteDatabase.execSQL(insr);
        }
    }
}
