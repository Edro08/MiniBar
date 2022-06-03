package com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces;

import android.content.Context;

public interface IDetalleReporteCons_View {
    void TableLayoutllenarFilas();
    boolean GenerarReporte(String nombreReporte);
    void CompartirReporte(String nombreReporte);
}
