import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String header = "templates/header.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "Wildlife Tracker");
      model.put("header", header);
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "Add New Animal");
      model.put("header", header);
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new/add", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Animal newAnimal = new Animal(name);
      newAnimal.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered-animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("title", "Add New Endangered Animal");
      model.put("header", header);
      model.put("template", "templates/endangered-animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered-animal/new/add", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name);
      newEndangeredAnimal.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/:name/:id/details", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      model.put("title", animal.getName() + "'s Details");
      model.put("header", header);
      model.put("animal", animal);
      model.put("sightings", animal.getSightings());
      model.put("location1", Sighting.LOCATION_1);
      model.put("location2", Sighting.LOCATION_2);
      model.put("location3", Sighting.LOCATION_3);
      model.put("location4", Sighting.LOCATION_4);
      model.put("template", "templates/animal-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/:name/:id/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      int animalId = animal.getId();
      String location = request.queryParams("location");
      String ranger = request.queryParams("ranger");
      Sighting newSighting = new Sighting(location, ranger, animalId);
      try {
        newSighting.save();
      } catch (IllegalArgumentException exception){
        response.redirect("/animal/" + animal.getName() + "/" + animal.getId() + "/details");
        return new ModelAndView(model, layout);
      }
      response.redirect("/animal/" + animal.getName() + "/" + animal.getId() + "/details");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered-animal/:name/:id/details", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      model.put("title", endangeredAnimal.getName() + "'s Details");
      model.put("header", header);
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("sightings", endangeredAnimal.getSightings());
      model.put("location1", Sighting.LOCATION_1);
      model.put("location2", Sighting.LOCATION_2);
      model.put("location3", Sighting.LOCATION_3);
      model.put("location4", Sighting.LOCATION_4);
      model.put("health1", Sighting.HEALTH_1);
      model.put("health2", Sighting.HEALTH_2);
      model.put("health3", Sighting.HEALTH_3);
      model.put("age1", Sighting.AGE_1);
      model.put("age2", Sighting.AGE_2);
      model.put("age3", Sighting.AGE_3);
      model.put("template", "templates/endangered-animal-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered-animal/:name/:id/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      int endangeredAnimalId = endangeredAnimal.getId();
      String location = request.queryParams("location");
      String ranger = request.queryParams("ranger");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      Sighting newSighting = new Sighting(location, ranger, health, age, endangeredAnimalId);
      try {
        newSighting.save();
      } catch (IllegalArgumentException exception){
        response.redirect("/endangered-animal/" + endangeredAnimal.getName() + "/" + endangeredAnimal.getId() + "/details");
        return new ModelAndView(model, layout);
      }
      response.redirect("/endangered-animal/" + endangeredAnimal.getName() + "/" + endangeredAnimal.getId() + "/details");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/:name/:id/:sighting_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      model.put("title", animal.getName() + "'s Details");
      model.put("header", header);
      model.put("animal", animal);
      model.put("sighting", sighting);
      model.put("location1", Sighting.LOCATION_1);
      model.put("location2", Sighting.LOCATION_2);
      model.put("location3", Sighting.LOCATION_3);
      model.put("location4", Sighting.LOCATION_4);
      model.put("template", "templates/animal-sighting-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/:name/:id/:sighting_id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      String location = request.queryParams("location");
      String ranger = request.queryParams("ranger");
      try {
        sighting.update(location, ranger);
      } catch (IllegalArgumentException exception){
        response.redirect("/animal/" + animal.getName() + "/" + animal.getId() + "/" + sighting.getId() + "/edit");
        return new ModelAndView(model, layout);
      }
      response.redirect("/animal/" + animal.getName() + "/" + animal.getId() + "/" + sighting.getId() + "/edit");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/:name/:id/:sighting_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      sighting.delete();
      response.redirect("/animal/" + animal.getName() + "/" + animal.getId() + "/" + "/details");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered-animal/:name/:id/:sighting_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      model.put("title", endangeredAnimal.getName() + "'s Details");
      model.put("header", header);
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("sighting", sighting);
      model.put("location1", Sighting.LOCATION_1);
      model.put("location2", Sighting.LOCATION_2);
      model.put("location3", Sighting.LOCATION_3);
      model.put("location4", Sighting.LOCATION_4);
      model.put("health1", Sighting.HEALTH_1);
      model.put("health2", Sighting.HEALTH_2);
      model.put("health3", Sighting.HEALTH_3);
      model.put("age1", Sighting.AGE_1);
      model.put("age2", Sighting.AGE_2);
      model.put("age3", Sighting.AGE_3);
      model.put("template", "templates/endangered-animal-sighting-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered-animal/:name/:id/:sighting_id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      String location = request.queryParams("location");
      String ranger = request.queryParams("ranger");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      try {
        sighting.updateEndangered(location, ranger, health, age);
      } catch (IllegalArgumentException exception){
        response.redirect("/endangered-animal/" + endangeredAnimal.getName() + "/" + endangeredAnimal.getId() + "/" + sighting.getId() + "/edit");
        return new ModelAndView(model, layout);
      }
      response.redirect("/endangered-animal/" + endangeredAnimal.getName() + "/" + endangeredAnimal.getId() + "/" + sighting.getId() + "/edit");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered-animal/:name/:id/:sighting_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(request.params(":name"), Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":sighting_id")));
      sighting.delete();
      response.redirect("/endangered-animal/" + endangeredAnimal.getName() + "/" + endangeredAnimal.getId() + "/details");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
