package com.vecks.timo.interfaces;

import com.vecks.timo.models.Tarea;

/**
 * Created by carlos on 12/12/17.
 */

public interface OnClickTarea {
    public void intentRegistroTareaActivity(int idTarea);
    public void actualizaStatus(Tarea tarea, int status);
}
