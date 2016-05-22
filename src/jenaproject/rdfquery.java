package jenaproject;

import java.util.*;

import org.apache.jena.util.FileManager;
import java.io.PrintWriter;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.util.Iterator; 

import com.hp.hpl.jena.rdf.model.*;


public class rdfquery {


	public static void main(String[] args)
	{
	

		FileManager.get().addLocatorClassLoader(rdfquery.class.getClassLoader());
		Model model = FileManager.get().loadModel("E:/jenaworkspace/jenaproject/src/jenaproject/data.rdf");
		String queryString = 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"PREFIX rdf : <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
				" PREFIX  : <http://http://purl.org/vocab/relationship#>"+
						
						"SELECT * WHERE {" +
						"?ancestorOf ?x. "+
						"}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try{
			ResultSet results = qexec.execSelect();
			while(results.hasNext()){
				QuerySolution soln= results.nextSolution();
				Literal name= soln.getLiteral("x");
				System.out.println(name);
			}
		}finally {
			qexec.close();
		}

	}
}
