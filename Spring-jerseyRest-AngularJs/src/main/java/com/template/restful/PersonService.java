package com.template.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.template.domain.Person;

@Path("/person")
public class PersonService
{

   @GET
   @Produces({MediaType.APPLICATION_JSON})
   @Path("dummy")
   public Person retrievePerson()
   {
      Person dummy = new Person();
      dummy.setName("Jose");
      dummy.setAddress("House");
      dummy.setHobby("Coding1");

      return dummy;
   }

}
