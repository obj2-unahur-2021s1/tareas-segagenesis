package ar.edu.unahur.obj2.tareas

abstract class Tarea(val horasEstimadas: Int, val responsable: Empleado  ) {

    abstract fun nominaEmpleados(): List<Empleado>

    abstract fun horasParaFinalizarTarea():Int

    abstract fun costo(): Int

 }

class TareaSimple(val costoInfraestructura: Int,val empleados: List<Empleado>, horasEstimadas: Int, responsable: Empleado) : Tarea(horasEstimadas, responsable) {

    override fun nominaEmpleados() = this.empleados + this.responsable

    override fun horasParaFinalizarTarea() = this.horasEstimadas + this.cantidadEmpleados()

    override fun costo() = this.costoEmpleados() + this.costoResponsable() + this.costoInfraestructura

    fun cantidadEmpleados() = this.empleados.size

    fun costoEmpleados() = (this.horasEstimadas / this.cantidadEmpleados()) * this.sueldoPorHoraEmpleados()

    fun sueldoPorHoraEmpleados() = this.empleados.sumBy { it.costoPorHora }

    fun costoResponsable() = this.horasEstimadas * this.responsable.costoPorHora
}