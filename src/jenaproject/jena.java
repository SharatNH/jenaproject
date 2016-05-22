package jenaproject;

import java.io.PrintWriter;
import java.util.*;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.util.Iterator; 

import com.hp.hpl.jena.rdf.model.*;

/**
 * A small family tree held in a Jena Model
 */
public class jena {
	public static void main(String args[]) {

		int count=0;
		// Namespace declarations
		String familyUri = "http://family#";
		String relationshipUri = "http://purl.org/vocab/relationship/";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix( "relation", "http://family#" );


		Resource gajanan = model.createResource(familyUri+"gajanan");
		Resource bhagirathi = model.createResource(familyUri+"bhagirathi");
		Resource krishna = model.createResource(familyUri+"krishna");
		Resource mahalaxhmi = model.createResource(familyUri+"mahalaxhmi");
		Resource shanta = model.createResource(familyUri+"shanta");

		Resource sharat = model.createResource(familyUri+"sharat");
		Resource sunil = model.createResource(familyUri+"sunil");

		Resource narayan = model.createResource(familyUri+"narayan");

		Resource syndicatebank = model.createResource(familyUri+"syndicatebank");



		Property ancestorOf = model.createProperty(relationshipUri,"ancestorOf");
		Property childOf = model.createProperty(relationshipUri,"childOf");
		Property closeFriendOf = model.createProperty(relationshipUri,"closeFriendOf");
		Property colleagueOf = model.createProperty(relationshipUri,"colleagueOf");
		Property employedBy = model.createProperty(relationshipUri,"employedBy");
		Property engagedTo = model.createProperty(relationshipUri,"engagedTo");
		Property friendOf = model.createProperty(relationshipUri,"friendOf");
		Property grandchildOf = model.createProperty(relationshipUri,"grandchildOf");
		Property grandparentOf = model.createProperty(relationshipUri,"grandparentOf");
		//Property hasMet= model.createProperty(relationshipUri,"hasMet");
		Property influencedBy = model.createProperty(relationshipUri,"influencedBy");
		Property livesWith = model.createProperty(relationshipUri,"livesWith");
		Property lostContactWith = model.createProperty(relationshipUri,"lostContactWith");
		Property parentOf = model.createProperty(relationshipUri,"parentOf");
		Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
		Property spouseOf = model.createProperty(relationshipUri,"spouseOf");
		Property employerOf = model.createProperty(relationshipUri,"employerOf");

		bhagirathi.addProperty(grandparentOf, "Sharat");
		bhagirathi.addProperty(ancestorOf,"Sharat");
		bhagirathi.addProperty(ancestorOf,"Sunil");
		bhagirathi.addProperty(grandparentOf, "Sunil");
		bhagirathi.addProperty(parentOf, "shanta");
		bhagirathi.addProperty(parentOf, "mahalaxhmi");
		bhagirathi.addProperty(parentOf, "krishna");

		gajanan.addProperty(grandparentOf, "Sharat");
		gajanan.addProperty(ancestorOf,"Sharat");
		gajanan.addProperty(ancestorOf,"Sunil");
		gajanan.addProperty(grandparentOf, "Sunil");
		gajanan.addProperty(parentOf, "shanta");
		gajanan.addProperty(parentOf, "mahalaxhmi");
		gajanan.addProperty(parentOf, "krishna");

		shanta.addProperty(siblingOf,"mahalaxhmi");
		shanta.addProperty(siblingOf, "krishna");
		shanta.addProperty(spouseOf,"narayan");
		shanta.addProperty(parentOf,"sharat");
		shanta.addProperty(employedBy, "sbi");

		krishna.addProperty(siblingOf,"shanta");
		krishna.addProperty(siblingOf,"mahalaxhmi");
		krishna.addProperty(spouseOf,"dhakshayani");
		krishna.addProperty(parentOf,"guru");
		krishna.addProperty(parentOf,"ganapathi");


		mahalaxhmi.addProperty(siblingOf,"shanta");
		mahalaxhmi.addProperty(siblingOf,"krishna");
		mahalaxhmi.addProperty(spouseOf,"narashimha");
		mahalaxhmi.addProperty(livesWith,"narashimha");
		mahalaxhmi.addProperty(parentOf,"sunil");

		sharat.addProperty(childOf,"shanta");
		sharat.addProperty(childOf,"narayan");
		sharat.addProperty(grandchildOf,"bhagirathi");
		sharat.addProperty(grandchildOf,"gajanan");
		sharat.addProperty(influencedBy,"apjkalam");
		sharat.addProperty(closeFriendOf,"rakesh");
		sharat.addProperty(friendOf,"sanketh");
		sharat.addProperty(lostContactWith, "ashrith");
		sharat.addProperty(livesWith, "sunil");

		narayan.addProperty(siblingOf,"raju");
		narayan.addProperty(spouseOf,"shanta");
		narayan.addProperty(parentOf,"sharat");
		narayan.addProperty(colleagueOf,"sundresh");
		narayan.addProperty(employedBy,"syndicatebank");

		sunil.addProperty(engagedTo, "sumana");
		sunil.addProperty(childOf, "mahalaxhmi");
		sunil.addProperty(childOf, "Narashimaha");
		sunil.addProperty(grandchildOf, "bhagirathi");
		sunil.addProperty(grandchildOf, "Gajanan");

		syndicatebank.addProperty(employerOf, "Narayan Hegde");

		model.write(new PrintWriter(System.out));

		System.out.println("/////////");
		System.out.println("Who is the grand parent of Sharat");
		NodeIterator query1 = model.listObjectsOfProperty(sharat,grandchildOf);

		while (query1.hasNext()) {
			System.out.println(query1.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("How many children does Bhagirathi has?");
		NodeIterator query2 = model.listObjectsOfProperty(bhagirathi,parentOf);


		while (query2.hasNext()) {
			query2.nextNode().toString();
			count++;

		}
		System.out.println("Number of children does Grand mother Bhagirathi has :"+count);


		System.out.println("/////////");
		System.out.println("Who is the sunil engaged to?");
		NodeIterator query3 = model.listObjectsOfProperty(sunil,engagedTo);

		while (query3.hasNext()) {
			System.out.println(query3.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who is the colleague of Narayan Hegde");
		NodeIterator query4 = model.listObjectsOfProperty(narayan,colleagueOf);

		while (query4 .hasNext()) {
			System.out.println(query4.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Where is the shanta hegde employed");
		NodeIterator query5 = model.listObjectsOfProperty(shanta,employedBy);

		while (query5 .hasNext()) {
			System.out.println(query5.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Where is the narayan hegde employed");
		NodeIterator query6 = model.listObjectsOfProperty(narayan,employedBy);

		while (query6 .hasNext()) {
			System.out.println(query6.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who are the grandchildren of Gajanan");
		NodeIterator query7 = model.listObjectsOfProperty(gajanan,grandparentOf);

		while (query7 .hasNext()) {
			System.out.println(query7.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who are the grandparent of Sunil");
		NodeIterator query8 = model.listObjectsOfProperty(sunil,grandchildOf);

		while (query8.hasNext()) {
			System.out.println(query8.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who has Sharat lost contact with?");
		NodeIterator query9 = model.listObjectsOfProperty(sharat,lostContactWith);

		while (query9.hasNext()) {
			System.out.println(query9.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who are the siblings of Shanta?");
		NodeIterator query10 = model.listObjectsOfProperty(shanta,siblingOf);

		while (query10.hasNext()) {
			System.out.println(query10.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Sharat has been influenced by?");
		NodeIterator query11 = model.listObjectsOfProperty(sharat,influencedBy);

		while (query11.hasNext()) {
			System.out.println(query11.nextNode().toString());
		}

		System.out.println("/////////");
		System.out.println("Who is the close friend of Sharat?");
		NodeIterator query12 = model.listObjectsOfProperty(sharat,closeFriendOf);

		while (query12.hasNext()) {
			System.out.println(query12.nextNode().toString());
		}


	}



}


