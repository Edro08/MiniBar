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
        //Datos para tabla Habitación
        consultaSql = "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Habitacion Deluxe H1',1)";
        sqLiteDatabase.execSQL(consultaSql);
        consultaSql = "INSERT INTO HABITACION(NOMBREHABITACION,IDESTADO) VALUES('Habitacion normal 2 camas',1)";
        sqLiteDatabase.execSQL(consultaSql);

        //Datos para tabla InventarioHabitación
        consultaSql = "INSERT INTO INVENTARIOHABITACION(IDHABITACION,IDPRODUCTO,EXISTENCIAS,CANTIDADMINIMA) " +
                "VALUES(1,1,10,5)";
        sqLiteDatabase.execSQL(consultaSql);
        consultaSql = "INSERT INTO INVENTARIOHABITACION(IDHABITACION,IDPRODUCTO,EXISTENCIAS,CANTIDADMINIMA) " +
                "VALUES(1,2,10,5)";
        sqLiteDatabase.execSQL(consultaSql);
    }

    public void Asistencia(SQLiteDatabase sqLiteDatabase)
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
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Nueces Nature Valley',1, 1.50, 1, 'https://m.media-amazon.com/images/I/91-uBXgO5ML._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Yuca',1, 1.75, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.thespruceeats.com%2Fcassava-or-yuca-chips-2137745&psig=AOvVaw2TBa12UgJZO5PB_3RRdpvn&ust=1654312186321000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCPDPi6ankPgCFQAAAAAdAAAAABAE');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Platano',1, 1.75, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.recetin.com%2Ftrucos-de-cocina-como-preparar-chips-de-platano.html&psig=AOvVaw1HphuTkmzr0UE4xZSz3Hg3&ust=1654312259441000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCOD4mMynkPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chips de Camote',1, 2.50, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tuhogar.com%2Fes-gt%2Frecetas%2Fsnacks-bebidas-y-postres%2Freceta-de-chips-de-camote&psig=AOvVaw1YglcfMGxV1qUhoTNrHIot&ust=1654312321827000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIj4r-inkPgCFQAAAAAdAAAAABAE');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Granuts - Nueces mixtas',1, 1.50, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pricesmart.com%2Fsite%2Fsv%2Fes%2Fpagina-producto%2F334892%3FchangeLanguage%3Dtrue&psig=AOvVaw3szDcggG6xGcwZAcQQPJty&ust=1654312390039000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCKiR1YaokPgCFQAAAAAdAAAAABAR');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Papas Pringles',1, 2.25, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fbaymarsa.com%2Findex.php%3Froute%3Dproduct%2Fproduct%26product_id%3D271&psig=AOvVaw2YSEtzJvTeftipZoohj_RN&ust=1654312486428000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMCnwbOokPgCFQAAAAAdAAAAABAL');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pistachos',1, 2.00, 1, 'https://m.media-amazon.com/images/I/71kjYpSZBsS._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Barra de Protehina Nature Valley',1, 1.50, 1, 'https://m.media-amazon.com/images/I/71sos+Cv31L._SL1500_.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Garbanzos Horneados c/Sal',1, 2.00, 1, 'https://static.wixstatic.com/media/a4cdba_6a83b77a79a74b2995d15001060f4198~mv2.jpg/v1/fill/w_3752,h_3920,al_c,q_85/a4cdba_6a83b77a79a74b2995d15001060f4198~mv2.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Tasu Fruta Deshidratada',1, 2.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Ftasusnacksmx.com%2F&psig=AOvVaw1GOyMrvhuaA7VHiFVasHqD&ust=1654312744140000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNjcrMypkPgCFQAAAAAdAAAAABAI');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Pasas Cubiertos de Chocolate',1, 3.25, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fchocolatescostanzo.com%2F2014%2Fproductos%2F04ChocolatesSinEnvolverYSemillasCubiertas%2Fpasa_con_chocolate.htm&psig=AOvVaw1uoB9qCEs7Ppy-78ldFGFL&ust=1654312853955000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCLiAn-WpkPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Arandanos Cubiertos de Chocolate',1, 3.50, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Flistado.mercadolibre.com.mx%2Far%25C3%25A1ndanos-con-chocolate-turin&psig=AOvVaw2S0Cs8H-Z4MH2nA8IuLgyT&ust=1654312886400000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMCZzfOpkPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Mini Barra de Chocolate',1, 3.00, 1, 'https://m.media-amazon.com/images/I/61axB7x5yNL._AC_SY879_.jpg');",
                //PRODUCTOS CATEGORIA BEBIDAS FRIAS
                //SODAS
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral Kinkey',2, 2.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.farmaciasannicolas.com%2FProducto%2FDetalle%2FKINLEY-CLUB-SODA-12-OZ%2Ff73eb5f6-44c6-4b38-b7c6-f403b7eef141&psig=AOvVaw0Kb_ItHTGxVz6FPkvPUXTU&ust=1654313258761000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIjYu6SrkPgCFQAAAAAdAAAAABAF');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Kirkland jugo Organico',2, 1.50, 1, 'https://www.grocery.com/store/image/cache/catalog/kirkland-signature/costco-B01LTI04R0-600x600.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Agua Mineral San Pellegrino',2, 4.00, 1, 'https://asset4.farmsteadapp.com/uploads/item/image/668/thumb_400_san-pellegrino-sparkling-mineral-water-in-glass-750-ml.jpg');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Coca Cola',2, 2.00, 1, 'https://media.officedepot.com/images/f_auto,q_auto,e_sharpen,h_450/products/208206/208206_p/208206_p');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Electrolit',2, 4.00, 1, 'https://images.heb.com/is/image/HEBGrocery/003314502?fit=constrain,1&wid=800&hei=800&fmt=jpg&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0');",
                //alcohocilas
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Chardonnay Vino Blanco',2, 10.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vinoselkiosco.com%2Ftienda%2Fvino-frances-bg-reserve-chardonnay-750-ml%2F&psig=AOvVaw0Xw-UFPWlXlHezRZCUglKv&ust=1654314038583000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNDiiZqukPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('B&G Merlot Vino Tinto',2, 10.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vivino.com%2FUS%2Fen%2Fbarton-guestier-b-g-reserve-merlot%2Fw%2F1132440&psig=AOvVaw2Uzsuvh5maz1X7_2lZug1j&ust=1654314068421000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJDC1aeukPgCFQAAAAAdAAAAABAT');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza Heineken',2, 4.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.heineken.com%2F&psig=AOvVaw3RTMDhYt-pn6rwWp9s0pGd&ust=1654314106942000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNj23LiukPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza IPA Squirrel Eye Brewing',2, 5.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Funtappd.com%2Fb%2Fsquirrel-eye-brewing-hi-pa%2F4109604&psig=AOvVaw273_9g_XLbSMqQquKuyHbY&ust=1654314138657000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJir3ceukPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Cerveza APA Squirrel Eye Brewing',2, 5.30, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fsebrewingco.com%2F&psig=AOvVaw273_9g_XLbSMqQquKuyHbY&ust=1654314138657000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJir3ceukPgCFQAAAAAdAAAAABAM');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Lager',2, 2.35, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fsantocoraje.com%2Fproducto%2Fpremium-lager-lata-12-oz%2F&psig=AOvVaw0DSlsQmcPRG9tjB96KoZ5y&ust=1654314236083000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJivk_uukPgCFQAAAAAdAAAAABAD');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Santo Coraje Pale Ale',2, 5.00, 1, 'Chandon Vino Blanco Espumante-');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Chandon Vino Blanco Espumante',2, 10.00, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.amazon.com.mx%2FVino-Espumoso-Chandon-Brut-750%2Fdp%2FB00O366OX4&psig=AOvVaw0-MXuWwp3MS5yE5UBaIfPD&ust=1654314422412000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCKjPxNGvkPgCFQAAAAAdAAAAABAQ');",
                "INSERT INTO PRODUCTO(PRODUCTONOMBRE, IDCATEGORIA, PRECIOUNITARIO, IDESTADO, IMAGENURL) VALUES ('Fitzer Agua Brava',2, 3.35, 1, 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.barriomexico.shop%2Fproducts%2Ffitzer-hard-seltzer-mix-pack&psig=AOvVaw1iiKdDX5FSNo626gnrEZZH&ust=1654314491437000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJjKpJmwkPgCFQAAAAAdAAAAABAI');",

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
