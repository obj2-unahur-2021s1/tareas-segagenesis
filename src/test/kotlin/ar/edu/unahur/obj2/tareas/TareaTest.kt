package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

 class TareaTest : DescribeSpec({
    describe("Una tarea") {
      val empleadoResponsable = Empleado(1000)
      val empleado1 = Empleado(500)
      val empleado2 = Empleado(500)
      val tarea = TareaSimple(1000, listOf(empleado1,empleado2),50,empleadoResponsable)

      it("Nomina de empleados que es una lista con los empleados y el responsable") {
        tarea.nominaEmpleados().shouldBe(listOf(empleado1,empleado2,empleadoResponsable))
      }

      it("Horas que se necesitan para finalizar que es la suma de las horas estimadas mas la cantidad de empleados ") {
        tarea.horasParaFinalizarTarea().shouldBe(52)
      }

      it("El costo que es la suma del costo de los empleados, el costo del responsable y el costo de la infraestructura") {
        tarea.costo().shouldBe(76000)
      }
    }
})
