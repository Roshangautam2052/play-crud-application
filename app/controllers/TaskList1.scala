package controllers

import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject._

@Singleton
class TaskList1 @Inject()(cc:ControllerComponents) extends AbstractController(cc) {

  def login: Action[AnyContent] = Action  {
    Ok(views.html.login1())
  }

  def validateLoginGet(userName:String, password:String) = Action {
    Ok(s"$userName logged in with password $password")
  }

  def validateLoginPost = Action { request  =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val userName = args("userName").head
      val password = args("password").head
      Redirect(routes.TaskList1.taskList)
    }.getOrElse(Redirect(routes.TaskList1.login)
    )

  }


  def taskList: Action[AnyContent] = Action {
    val tasks = List("task1", "task2", "task3", "Values")
    Ok(views.html.taskLists1(tasks))
  }

}
