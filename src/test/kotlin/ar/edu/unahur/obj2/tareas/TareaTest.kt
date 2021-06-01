package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

 class TareaTest : DescribeSpec({

    describe("Una tarea") {
      val responsable = Empleado(1000)
      val empleado1 = Empleado(500)
      val empleado2 = Empleado(500)
      val tarea = TareaSimple(50, 1000,listOf(empleado1,empleado2),responsable)


      it("Nomina de empleados que es una lista con los empleados y el responsable") {
        tarea.nominaEmpleados().shouldBe(listOf(empleado1,empleado2,responsable))
      }

      it("Horas que se necesitan para finalizar que es la suma de las horas estimadas dividido la cantidad de empleados ") {
        tarea.horasParaFinalizarTarea().shouldBe(25)
      }

      it("El costo que es la suma del costo de los empleados, el costo del responsable y el costo de la infraestructura") {
        tarea.costo().shouldBe(76000)
      }
    }

   describe("Una tarea de integracion") {
     val empleado1 = Empleado(500)
     val empleado2 = Empleado(500)
     val responsable1 = Empleado(1000)
     val responsable2 = Empleado(1000)
     val responsableIntegracion = Empleado(1000)
     val tarea1 = TareaSimple(50,1000, listOf(empleado1),responsable1)
     val tarea2 = TareaSimple(50,1000, listOf(empleado2),responsable2)
     val tareaIntegracion = TareaIntegracion(responsableIntegracion, listOf(tarea1,tarea2))

     it("Nomina de empleados que es la suma de las nóminas de las subtareas más el responsable de la tarea de integración. "){
       tareaIntegracion.nominaEmpleados().shouldBe(listOf(empleado1,responsable1,empleado2,responsable2,responsableIntegracion))
     }

     it("Las horas necesarias para realizarla se calculan como la suma de lo que tardan sus subtareas más una hora para reuniones de planificación por cada 8 horas de trabajo real") {
       tareaIntegracion.horasParaFinalizarTarea().shouldBe(112)
     }

     it("El costo es la suma de los costos de sus subtareas más un bonus que se le paga al responsable, equivalente al 3% de esa suma.") {
       tareaIntegracion.costo().shouldBe(156560)
     }
   }
})
