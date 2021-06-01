package ar.edu.unahur.obj2.tareas

abstract class Tarea(val responsable: Empleado  ) {

    abstract fun nominaEmpleados(): List<Empleado>

    abstract fun horasParaFinalizarTarea():Int

    abstract fun costo(): Int
 }

class TareaSimple(val horasEstimadas: Int, val costoInfraestructura: Int,val empleados: List<Empleado>, responsable: Empleado) : Tarea( responsable) {

    override fun nominaEmpleados() = this.empleados + this.responsable

    override fun horasParaFinalizarTarea() = this.horasEstimadas / this.cantidadEmpleados()

    override fun costo() = this.costoEmpleados() + this.costoResponsable() + this.costoInfraestructura

    fun cantidadEmpleados() = this.empleados.size

    fun costoEmpleados() = (this.horasEstimadas / this.cantidadEmpleados()) * this.sueldoPorHoraEmpleados()

    fun sueldoPorHoraEmpleados() = this.empleados.sumBy { it.costoPorHora }

    fun costoResponsable() = this.horasEstimadas * this.responsable.costoPorHora
}

class TareaIntegracion(responsable: Empleado, val subtareas: List<TareaSimple>) : Tarea(responsable) {

    override fun nominaEmpleados() = this.nominaSubtareas() + this.responsable

    override fun horasParaFinalizarTarea() =  this.horasParaFinalizarSubtareas() + this.tiempoPlanificacion()

    override fun costo() = this.costoTareasACoordinar() + (this.costoTareasACoordinar() * 3 / 100)

    fun horasParaFinalizarSubtareas() = this.subtareas.sumBy { it.horasParaFinalizarTarea() }

    fun nominaSubtareas() = this.subtareas.flatMap { it.nominaEmpleados()  }

    fun tiempoPlanificacion() = this.horasParaFinalizarSubtareas() / 8

    fun costoTareasACoordinar() = this.subtareas.sumBy { it.costo() }
}