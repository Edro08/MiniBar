package com.purosurf.minibar.Presentador.AdministradorEmpleado.Interfaces;

import android.content.Context;
import android.database.Cursor;

public interface IDetalleReporteConsPresentador {
    Cursor DatosConsumoHabitacion(Context context, int IdConsumo);
    Cursor DatosInventario(Context context);
    Cursor DatosCompra(Context context, String FechaDesde, String FechaHasta);
}
