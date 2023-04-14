package msc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

public class DataGeneration {

	public static void main(String[] args)
			throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
		// TODO Auto-generated method stub
		String baseDirString = System.getProperty("user.dir");
		String filePath = baseDirString + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "ZIMO_App_KnowledgeBase.owl";
		String outFile = baseDirString + File.separator + "src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "results" + File.separator;

		File file = new File(filePath);

		File outFileObj;
		String fileName;
		int fileIdentifier;
		do {
			fileIdentifier = random.nextInt(100000);
			fileName = outFile + "ontologyZIMO" + fileIdentifier + ".owl";
			outFileObj = new File(fileName);
		} while (outFileObj.exists());

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology importedOntology = manager.loadOntologyFromOntologyDocument(file);
		ontology = manager
				.createOntology(IRI.create("http://www.semanticweb.org/hungbui/ontologies/2022/0/ZIMOAPP-Instances"));
		OWLImportsDeclaration declaration = manager.getOWLDataFactory()
				.getOWLImportsDeclaration(IRI.create(ontologyPrefix));
		manager.applyChange(new AddImport(ontology, declaration));
		dataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
		System.out.println(ontologyPrefix);
		generateDataProperties();
		generateNetworkFlavour();
		generateCloudFlavour();
		generateCloudSlice();
		generateApp_AppUser();
		Matching_Cloud_Slice();
		generateNetworkSlice();
		
		//create data from DB, including UserApp_request, App_Test and Slice_Test
		//-------------------------------------------------
		create_UserApp_request();
		create_App_Test();
		create_Network_Slice_1_and_2();
		create_Network_Slice_3();
		//-------------------------------------------------
		manager.saveOntology(ontology, new RDFXMLDocumentFormat(), new FileOutputStream(fileName));

		printData(fileIdentifier);
		System.out.println("Done");
	}

	private static void create_UserApp_request() {
		OWLNamedIndividual user_app = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#UserApp_Test");
		OWLClass class_UserApp = dataFactory.getOWLClass(ontologyPrefix + "#UserApp");
		OWLDeclarationAxiom declarationAxiom_UserApp = dataFactory.getOWLDeclarationAxiom(user_app);
		ontology.add(declarationAxiom_UserApp);
		OWLClassAssertionAxiom classAssertionAxiom_UserApp = dataFactory.getOWLClassAssertionAxiom(class_UserApp,
				user_app);
		ontology.add(classAssertionAxiom_UserApp);

		OWLObjectProperty from_App = dataFactory.getOWLObjectProperty(ontologyPrefix + "#from_App");
		// change App
		OWLNamedIndividual app = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#App_Test");
		OWLObjectPropertyAssertionAxiom ObjPropAssertionAxiom = dataFactory.getOWLObjectPropertyAssertionAxiom(from_App,
				user_app, app);
		ontology.add(ObjPropAssertionAxiom);

		OWLNamedIndividual Star_Throughput = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Throughput");
		OWLNamedIndividual Star_Latency = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Latency");
		OWLNamedIndividual Star_Error_rate = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Error_rate");
		OWLNamedIndividual Star_Jitter = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Jitter");
		OWLNamedIndividual Star_Availability = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Availability");
		OWLNamedIndividual Star_Portability = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Portability");
		OWLNamedIndividual Star_Cost = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Cost");
		OWLNamedIndividual Star_Reliability = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Reliability");
		OWLNamedIndividual Star_Security = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + "4_Star_Security");
		OWLObjectProperty UserApp_require_Attributes = dataFactory
				.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_Attributes");
		OWLObjectPropertyAssertionAxiom UserApp_require_Throughput_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Latency);
		OWLObjectPropertyAssertionAxiom UserApp_require_Latency_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Throughput);
		OWLObjectPropertyAssertionAxiom UserApp_require_Error_rate_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Error_rate);
		OWLObjectPropertyAssertionAxiom UserApp_require_Jitter_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Jitter);
		OWLObjectPropertyAssertionAxiom UserApp_require_Availability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Availability);
		OWLObjectPropertyAssertionAxiom UserApp_require_Portability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Portability);
		OWLObjectPropertyAssertionAxiom UserApp_require_Cost_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Cost);
		OWLObjectPropertyAssertionAxiom UserApp_require_Reliability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Reliability);
		OWLObjectPropertyAssertionAxiom UserApp_require_Security_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Security);
		ontology.add(UserApp_require_Throughput_Axiom);
		ontology.add(UserApp_require_Latency_Axiom);
		ontology.add(UserApp_require_Error_rate_Axiom);
		ontology.add(UserApp_require_Jitter_Axiom);
		ontology.add(UserApp_require_Availability_Axiom);
		ontology.add(UserApp_require_Portability_Axiom);
		ontology.add(UserApp_require_Cost_Axiom);
		ontology.add(UserApp_require_Reliability_Axiom);
		ontology.add(UserApp_require_Security_Axiom);
	}

	private static void create_App_Test() {
		OWLNamedIndividual app = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#App_Test");
		OWLClass class_App = dataFactory.getOWLClass(ontologyPrefix + "#App");
		OWLDeclarationAxiom declarationAxiom_App = dataFactory.getOWLDeclarationAxiom(app);
		ontology.add(declarationAxiom_App);
		OWLClassAssertionAxiom classAssertionAxiom_UserApp = dataFactory.getOWLClassAssertionAxiom(class_App, app);
		ontology.add(classAssertionAxiom_UserApp);

		for (int i = 0; i < 4; i++) {
			OWLNamedIndividual appinstance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#AppInstance_App" + "_" + (i + 1));
			OWLClass class_App_Instance = dataFactory.getOWLClass(ontologyPrefix + "#App_Instance");
			OWLDeclarationAxiom declarationAxiom_AppInstance = dataFactory.getOWLDeclarationAxiom(appinstance);
			ontology.add(declarationAxiom_AppInstance);
			OWLClassAssertionAxiom classAssertionAxiom_AppInstance = dataFactory
					.getOWLClassAssertionAxiom(class_App_Instance, appinstance);
			ontology.add(classAssertionAxiom_AppInstance);
			// Match App to App Instance
			OWLObjectProperty has_App_Instance = dataFactory.getOWLObjectProperty(ontologyPrefix + "#has_App_Instance");
			OWLObjectPropertyAssertionAxiom hasAppInstance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_App_Instance, app, appinstance);
			ontology.add(hasAppInstance_Axiom);

			OWLObjectProperty has_Network_Flavour = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Flavour");
			OWLNamedIndividual networkFlavour = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#" + "Network_Flavour_Healthcare_" + (i * 3 + 1));
			OWLObjectPropertyAssertionAxiom has_Network_Flavour_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour, appinstance, networkFlavour);
			ontology.add(has_Network_Flavour_Axiom);
			OWLObjectProperty has_Cloud_Flavour = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Flavour");
			OWLNamedIndividual cloudFlavour = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#" + "Cloud_Flavour_5");
			OWLObjectPropertyAssertionAxiom has_Cloud_Flavour_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour, appinstance, cloudFlavour);
			ontology.add(has_Cloud_Flavour_Axiom);
		}
	}

	private static void create_Network_Slice_1_and_2() {
		for (int i = 0; i < 2; i++) {
			OWLNamedIndividual networkSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Test_" + (i + 1));
			OWLClass class_Network_slice = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_Network_Slice = dataFactory
					.getOWLClassAssertionAxiom(class_Network_slice, networkSlice);
			ontology.add(classAssertionAxiom_Network_Slice);
			OWLObjectProperty has_Network_Slice_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes");

			OWLNamedIndividual Delay_Tolerance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Delay_Tolerance_supported");
			;
			OWLNamedIndividual Mission_critical_support = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Mission_critical");
			OWLNamedIndividual Mission_critical_service_support2 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#MCData");
			OWLNamedIndividual Synchronicity_Availability = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Synchronicity_Availability_Between_BS_and_UE");
			OWLNamedIndividual V2X_communication_mode = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#V2X_communication_mode_YES_NR_and_E_UTRA");
			OWLNamedIndividual MPS = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MPS");
			OWLNamedIndividual GPP_5QI = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_2");
			;
			OWLNamedIndividual Availability;

			OWLNamedIndividual Guaranteed_downlink;
			OWLNamedIndividual Guaranteed_uplink;
			OWLNamedIndividual Max_downlink;
			OWLNamedIndividual Max_uplink;
			OWLNamedIndividual Max_downlink_per_UE;
			OWLNamedIndividual Max_uplink_per_UE;

			OWLNamedIndividual Velocity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_2");
			;

			if (i == 1 || i == 2)
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_3");
			else
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_2");
			if (i % 2 == 0) {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_1");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_1");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_1");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_1");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_1");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_1");

			} else {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_2");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_2");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_2");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_2");
			}
			;

			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Delay_Tolerance);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_service_support2);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Synchronicity_Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							V2X_communication_mode);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MPS);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, GPP_5QI);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Guaranteed_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Max_downlink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Velocity);

			ontology.add(NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_support_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_service_support2_Axiom);
			ontology.add(NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(NetworkSlice_has_V2X_communication_mode_Axiom);
			ontology.add(NetworkSlice_has_MPS_Axiom);
			ontology.add(NetworkSlice_has_Availability_Axiom);
			ontology.add(NetworkSlice_has_throughput_Axiom_1);
			ontology.add(NetworkSlice_has_throughput_Axiom_2);
			ontology.add(NetworkSlice_has_throughput_Axiom_3);
			ontology.add(NetworkSlice_has_throughput_Axiom_4);
			ontology.add(NetworkSlice_has_throughput_Axiom_5);
			ontology.add(NetworkSlice_has_throughput_Axiom_6);
			ontology.add(NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(NetworkSlice_has_Velocity);
		}
	}

	private static void create_Network_Slice_3() {
		OWLNamedIndividual networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Test_3");
		OWLClass class_Network_slice = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
		OWLClassAssertionAxiom classAssertionAxiom_Network_Slice = dataFactory
				.getOWLClassAssertionAxiom(class_Network_slice, networkSlice);
		ontology.add(classAssertionAxiom_Network_Slice);
		OWLObjectProperty has_Network_Slice_Attributes = dataFactory
				.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes");

		OWLNamedIndividual Delay_Tolerance = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#Delay_Tolerance_supported");
		OWLNamedIndividual DC_Availability = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#DC_Availability_supported");
		OWLNamedIndividual Group_communication_support = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#Group_communication_support_Broadcast/Multicast_+_SC-PTM");
		OWLNamedIndividual Mission_critical_support = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#Mission_critical");
		OWLNamedIndividual Mission_critical_capability_support = dataFactory.getOWLNamedIndividual(
				ontologyPrefix + "#Mission-critical_capability_support_Inter_user_prioritization");
		OWLNamedIndividual Mission_critical_service_support1 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#MCPTT");
		OWLNamedIndividual Mission_critical_service_support2 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#MCData");
		OWLNamedIndividual Mission_critical_service_support3 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#MCVideo");
		OWLNamedIndividual MMTel_support = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MMTel_supported");
		OWLNamedIndividual PS_Availability_1 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_AECID");
		OWLNamedIndividual PS_Availability_2 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_OTDOA");
		OWLNamedIndividual PS_Availability_3 = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_CIDE-CID");
		OWLNamedIndividual PS_Prediction_frequency = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#PS_Prediction_frequency_Per_minute");
		OWLNamedIndividual SCS_mode = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#SCS_mode1");
		OWLNamedIndividual Support_for_Non_IP_traffic = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#Support_for_non-IP_traffic_supported");
		OWLNamedIndividual Synchronicity_Availability = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#Synchronicity_Availability_Between_BS_and_UE_&_UE_and_UE");
		OWLNamedIndividual User_Management_Openness = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#User_management_openness_Supported");
		OWLNamedIndividual V2X_communication_mode = dataFactory
				.getOWLNamedIndividual(ontologyPrefix + "#V2X_communication_mode_YES_NR_and_E_UTRA");
		OWLNamedIndividual MPS = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MPS");
		OWLNamedIndividual GPP_5QI = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_2");
		;
		OWLNamedIndividual Availability;

		OWLNamedIndividual Guaranteed_downlink;
		OWLNamedIndividual Guaranteed_uplink;
		OWLNamedIndividual Max_downlink;
		OWLNamedIndividual Max_uplink;
		OWLNamedIndividual Max_downlink_per_UE;
		OWLNamedIndividual Max_uplink_per_UE;

		OWLNamedIndividual Velocity = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_3");
		;

		Availability = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_2");

		Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_4");
		Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_4");
		Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_4");
		Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_4");
		Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_4");
		Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_4");

		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Delay_Tolerance);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_DC_Availability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, DC_Availability);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Group_communication_support_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Group_communication_support);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Mission_critical_support);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_capability_support_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Mission_critical_capability_support);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support1_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Mission_critical_service_support1);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Mission_critical_service_support2);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support3_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Mission_critical_service_support3);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_MMTel_support_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MMTel_support);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_1_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_1);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_2_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_2);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_3_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_3);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Prediction_frequency_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						PS_Prediction_frequency);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_SCS_mode_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, SCS_mode);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Support_for_Non_IP_traffic_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Support_for_Non_IP_traffic);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						Synchronicity_Availability);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_User_Management_Openness_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
						User_Management_Openness);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, V2X_communication_mode);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_MPS_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MPS);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, GPP_5QI);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Availability_Axiom = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Availability);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_1 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_downlink);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_2 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_uplink);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_3 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_4 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_5 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink_per_UE);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_6 = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink_per_UE);
		OWLObjectPropertyAssertionAxiom NetworkSlice_has_Velocity = dataFactory
				.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Velocity);

		ontology.add(NetworkSlice_has_Delay_Tolerance_Axiom);
		ontology.add(NetworkSlice_has_DC_Availability_Axiom);
		ontology.add(NetworkSlice_has_Group_communication_support_Axiom);
		ontology.add(NetworkSlice_has_Mission_critical_support_Axiom);
		ontology.add(NetworkSlice_has_Mission_critical_capability_support_Axiom);
		ontology.add(NetworkSlice_has_Mission_critical_service_support1_Axiom);
		ontology.add(NetworkSlice_has_Mission_critical_service_support2_Axiom);
		ontology.add(NetworkSlice_has_Mission_critical_service_support3_Axiom);
		ontology.add(NetworkSlice_has_MMTel_support_Axiom);
		ontology.add(NetworkSlice_has_PS_Availability_1_Axiom);
		ontology.add(NetworkSlice_has_PS_Availability_2_Axiom);
		ontology.add(NetworkSlice_has_PS_Availability_3_Axiom);
		ontology.add(NetworkSlice_has_PS_Prediction_frequency_Axiom);
		ontology.add(NetworkSlice_has_SCS_mode_Axiom);
		ontology.add(NetworkSlice_has_Support_for_Non_IP_traffic_Axiom);
		ontology.add(NetworkSlice_has_Synchronicity_Availability_Axiom);
		ontology.add(NetworkSlice_has_User_Management_Openness_Axiom);
		ontology.add(NetworkSlice_has_V2X_communication_mode_Axiom);
		ontology.add(NetworkSlice_has_MPS_Axiom);
		ontology.add(NetworkSlice_has_Availability_Axiom);
		ontology.add(NetworkSlice_has_throughput_Axiom_1);
		ontology.add(NetworkSlice_has_throughput_Axiom_2);
		ontology.add(NetworkSlice_has_throughput_Axiom_3);
		ontology.add(NetworkSlice_has_throughput_Axiom_4);
		ontology.add(NetworkSlice_has_throughput_Axiom_5);
		ontology.add(NetworkSlice_has_throughput_Axiom_6);
		ontology.add(NetworkSlice_has_3GPP_5QI_Axiom);
		ontology.add(NetworkSlice_has_Velocity);
	}

	private static void generateApp_AppUser() {
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			// generate App Data
			OWLNamedIndividual app = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#App_" + (i + 1));
			OWLClass class_App = dataFactory.getOWLClass(ontologyPrefix + "#App");
			OWLDeclarationAxiom declarationAxiom_App = dataFactory.getOWLDeclarationAxiom(app);
			ontology.add(declarationAxiom_App);
			OWLClassAssertionAxiom classAssertionAxiom_App = dataFactory.getOWLClassAssertionAxiom(class_App, app);
			ontology.add(classAssertionAxiom_App);

			for (int j = 0; j < 10; j++) {
				// generate user_X_app_Y data | for each App, there are 10 User with different
				// requirements
				OWLNamedIndividual user_app = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#UserApp_" + (j + 1) + "_" + (i + 1));
				OWLClass class_UserApp = dataFactory.getOWLClass(ontologyPrefix + "#UserApp");
				OWLDeclarationAxiom declarationAxiom_UserApp = dataFactory.getOWLDeclarationAxiom(user_app);
				ontology.add(declarationAxiom_UserApp);
				OWLClassAssertionAxiom classAssertionAxiom_UserApp = dataFactory
						.getOWLClassAssertionAxiom(class_UserApp, user_app);
				ontology.add(classAssertionAxiom_UserApp);
				// generate DataProperties from_App
				OWLObjectProperty from_App = dataFactory.getOWLObjectProperty(ontologyPrefix + "#from_App");
				OWLObjectPropertyAssertionAxiom ObjPropAssertionAxiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(from_App, user_app, app);
				ontology.add(ObjPropAssertionAxiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV			from_App_Inv
				OWLObjectProperty from_App_Inv = dataFactory.getOWLObjectProperty(ontologyPrefix + "#from_App_Inv");
				OWLObjectPropertyAssertionAxiom Inv = dataFactory.getOWLObjectPropertyAssertionAxiom(from_App_Inv, app,
						user_app);
				ontology.add(Inv);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// generate DataProperties UserApp_require_Attributes UserApp_1_X requires 1
				// star,... REQUIREMENT
				OWLNamedIndividual Star_Throughput;
				OWLNamedIndividual Star_Latency;
				OWLNamedIndividual Star_Error_rate;
				OWLNamedIndividual Star_Jitter;
				OWLNamedIndividual Star_Availability;
				OWLNamedIndividual Star_Portability;
				OWLNamedIndividual Star_Cost;
				OWLNamedIndividual Star_Reliability;
				OWLNamedIndividual Star_Security;
				if (j < 5) {
					Star_Throughput = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Throughput");
					Star_Latency = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Latency");
					Star_Error_rate = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Error_rate");
					Star_Jitter = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Jitter");
					Star_Availability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Availability");
					Star_Portability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Portability");
					Star_Cost = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Cost");
					Star_Reliability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Reliability");
					Star_Security = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + (j + 1) + "_Star_Security");
				} else {
					Star_Throughput = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j) % 5 + 1) + "_Star_Throughput");
					Star_Latency = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j) % 5 + 1) + "_Star_Latency");
					Star_Error_rate = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j) % 5 + 1) + "_Star_Error_rate");
					Star_Jitter = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j) % 5 + 1) + "_Star_Jitter");
					Star_Availability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j + 3) % 5 + 1) + "_Star_Availability");
					Star_Portability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j + 1) % 5 + 1) + "_Star_Portability");
					Star_Cost = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j + 3) % 5 + 1) + "_Star_Cost");
					Star_Reliability = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j + 3) % 5 + 1) + "_Star_Reliability");
					Star_Security = dataFactory
							.getOWLNamedIndividual(ontologyPrefix + "#" + ((j + 3) % 5 + 1) + "_Star_Security");
				}
				OWLObjectProperty UserApp_require_Attributes = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_Attributes");
				OWLObjectPropertyAssertionAxiom UserApp_require_Throughput_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Latency);
				OWLObjectPropertyAssertionAxiom UserApp_require_Latency_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Throughput);
				OWLObjectPropertyAssertionAxiom UserApp_require_Error_rate_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Error_rate);
				OWLObjectPropertyAssertionAxiom UserApp_require_Jitter_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Jitter);
				OWLObjectPropertyAssertionAxiom UserApp_require_Availability_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Availability);
				OWLObjectPropertyAssertionAxiom UserApp_require_Portability_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Portability);
				OWLObjectPropertyAssertionAxiom UserApp_require_Cost_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Cost);
				OWLObjectPropertyAssertionAxiom UserApp_require_Reliability_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Reliability);
				OWLObjectPropertyAssertionAxiom UserApp_require_Security_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, user_app, Star_Security);
				ontology.add(UserApp_require_Throughput_Axiom);
				ontology.add(UserApp_require_Latency_Axiom);
				ontology.add(UserApp_require_Error_rate_Axiom);
				ontology.add(UserApp_require_Jitter_Axiom);
				ontology.add(UserApp_require_Availability_Axiom);
				ontology.add(UserApp_require_Portability_Axiom);
				ontology.add(UserApp_require_Cost_Axiom);
				ontology.add(UserApp_require_Reliability_Axiom);
				ontology.add(UserApp_require_Security_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV			UserApp_require_Attributes_Inv
				OWLObjectProperty UserApp_require_Attributes_Inv = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_Attributes_Inv");
				OWLObjectPropertyAssertionAxiom Inv1 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes_Inv, Star_Latency, user_app);
				OWLObjectPropertyAssertionAxiom Inv2 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Throughput, user_app);
				OWLObjectPropertyAssertionAxiom Inv3 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Error_rate, user_app);
				OWLObjectPropertyAssertionAxiom Inv4 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Jitter, user_app);
				OWLObjectPropertyAssertionAxiom Inv5 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Availability, user_app);
				OWLObjectPropertyAssertionAxiom Inv6 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Portability, user_app);
				OWLObjectPropertyAssertionAxiom Inv7 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Cost, user_app);
				OWLObjectPropertyAssertionAxiom Inv8 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Reliability, user_app);
				OWLObjectPropertyAssertionAxiom Inv9 = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Attributes, Star_Security, user_app);
				ontology.add(Inv1);
				ontology.add(Inv2);
				ontology.add(Inv3);
				ontology.add(Inv4);
				ontology.add(Inv5);
				ontology.add(Inv6);
				ontology.add(Inv7);
				ontology.add(Inv8);
				ontology.add(Inv9);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// generate App Instance Data | for each App, there are init 10 App Instance,
				// which sastify each UserApp requirements on App
				OWLNamedIndividual appinstance = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#AppInstance_" + (i + 1) + "_" + (j + 1));
				OWLClass class_App_Instance = dataFactory.getOWLClass(ontologyPrefix + "#App_Instance");
				OWLDeclarationAxiom declarationAxiom_AppInstance = dataFactory.getOWLDeclarationAxiom(appinstance);
				ontology.add(declarationAxiom_AppInstance);
				OWLClassAssertionAxiom classAssertionAxiom_AppInstance = dataFactory
						.getOWLClassAssertionAxiom(class_App_Instance, appinstance);
				ontology.add(classAssertionAxiom_AppInstance);
				// Match App to App Instance
				OWLObjectProperty has_App_Instance = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_App_Instance");
				OWLObjectPropertyAssertionAxiom hasAppInstance_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_App_Instance, app, appinstance);
				ontology.add(hasAppInstance_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV			has_App_Instance_Inv
				OWLObjectProperty has_App_Instance_Inv = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_App_Instance_Inv");
				OWLObjectPropertyAssertionAxiom has_App_Instance_Inv_axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_App_Instance_Inv, appinstance, app);
				ontology.add(has_App_Instance_Inv_axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// Match UserApp and AppInstance
				OWLObjectProperty UserApp_require_App_Instance = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_App_Instance");
				OWLObjectProperty App_Instance_Sastify_UserApp = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#App_Instance_Sastify_UserApp");
				OWLObjectPropertyAssertionAxiom UserApp_require_App_Instance_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_App_Instance, user_app, appinstance);
				OWLObjectPropertyAssertionAxiom App_Instance_Sastify_UserApp_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(App_Instance_Sastify_UserApp, appinstance, user_app);
				ontology.add(UserApp_require_App_Instance_Axiom);
				ontology.add(App_Instance_Sastify_UserApp_Axiom);

				// Match Network AppInstance to Network Flavour | 3App: Healthcare, 4App:
				// Gaming, 3App: Public_Safety
				OWLObjectProperty has_Network_Flavour = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_Network_Flavour");
				String NetworkFlavourType;
				if (i % 3 == 0)
					NetworkFlavourType = "Network_Flavour_Healthcare_";
				else if (i % 3 == 1)
					NetworkFlavourType = "Network_Flavour_Gaming_";
				else
					NetworkFlavourType = "Network_Flavour_Public_Safety_";
				OWLNamedIndividual networkFlavour = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkFlavourType + (j + 1));
				OWLObjectPropertyAssertionAxiom has_Network_Flavour_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour, appinstance, networkFlavour);
				ontology.add(has_Network_Flavour_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV			has_Network_Flavour_Inv
				OWLObjectProperty has_Network_Flavour_Inv = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_Network_Flavour_Inv");
				OWLObjectPropertyAssertionAxiom has_Network_Flavour_Inv_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Inv, networkFlavour, appinstance);
				ontology.add(has_Network_Flavour_Inv_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// Match Network Flavour vs Network Slice
				OWLNamedIndividual networkSlice;
				OWLObjectProperty UserApp_require_Network_Slice = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_Network_Slice");
				OWLObjectProperty Network_Slice_Sastify_UserApp = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#Network_Slice_Sastify_UserApp");
				String NetworkSliceType;
				if (i % 3 == 0)
					NetworkSliceType = "Network_Slice_Healthcare_";
				else if (i % 3 == 1)
					NetworkSliceType = "Network_Slice_Gaming_";
				else
					NetworkSliceType = "Network_Slice_Public_Safety_";

				if (j < 3)
					networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "1");
				else if (j < 5)
					networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "2");
				else if (j < 7)
					networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "3");
				else
					networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "4");

				OWLObjectPropertyAssertionAxiom UserApp_require_Network_Slice_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Network_Slice, user_app, networkSlice);
				OWLObjectPropertyAssertionAxiom Network_Slice_Sastify_UserApp_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(Network_Slice_Sastify_UserApp, networkSlice, user_app);
				ontology.add(UserApp_require_Network_Slice_Axiom);
				ontology.add(Network_Slice_Sastify_UserApp_Axiom);

				// Match Network AppInstance to Cloud Flavour
				OWLObjectProperty has_Cloud_Flavour = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Flavour");
				OWLNamedIndividual cloudFlavour = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Flavour_" + ((i % 5) + 1));
				OWLObjectPropertyAssertionAxiom has_Cloud_Flavour_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour, appinstance, cloudFlavour);
				ontology.add(has_Cloud_Flavour_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV			has_Cloud_Flavour_Inv
				OWLObjectProperty has_Cloud_Flavour_Inv = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Flavour_Inv");
				OWLObjectPropertyAssertionAxiom has_Cloud_Flavour_Inv_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Inv, cloudFlavour, appinstance);
				ontology.add(has_Cloud_Flavour_Inv_Axiom);

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// Match UserApp and Cloud_Slice
				OWLObjectProperty UserApp_require_Cloud_Slice = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#UserApp_require_Cloud_Slice");
				OWLObjectProperty Cloud_Slice_Sastify_UserApp = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#Cloud_Slice_Sastify_UserApp");
				OWLNamedIndividual cloudSlice = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Slice_" + ((i % 5) + 1));
				OWLObjectPropertyAssertionAxiom UserApp_require_Cloud_Slice_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(UserApp_require_Cloud_Slice, user_app, cloudSlice);
				OWLObjectPropertyAssertionAxiom Cloud_Slice_Sastify_UserApp_Axiom = dataFactory
						.getOWLObjectPropertyAssertionAxiom(Cloud_Slice_Sastify_UserApp, cloudSlice, user_app);
				ontology.add(UserApp_require_Cloud_Slice_Axiom);
				ontology.add(Cloud_Slice_Sastify_UserApp_Axiom);
			}
		}
	}

	private static void generateNetworkFlavour() {

		// generate NetworkFlavour Data | 30 Flavour apply to 10 type of App
		// Requirements on 3 category Gaming, Healthcare and Public Safetys.
		for (int i = 0; i < 30; i++) {
			OWLNamedIndividual networkFlavour;
			OWLNamedIndividual Business;
			OWLNamedIndividual Data_security;
			OWLNamedIndividual Data_Volume_per_device;
			OWLNamedIndividual Density;
			OWLNamedIndividual Energy_efficiency;
			OWLNamedIndividual Isolation;
			OWLNamedIndividual Latency;
			OWLNamedIndividual Reliability;
			OWLNamedIndividual Throughput;
			OWLNamedIndividual Traffic_type;
			OWLNamedIndividual Typical_data_profile;
			OWLNamedIndividual Device_Mobility;
			OWLNamedIndividual Device_Type;
			OWLNamedIndividual Operation;
			OWLNamedIndividual vertical;
			OWLObjectProperty has_Network_Flavour_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Flavour_Attributes");
			// Flavour for Healthcare
			if (i < 10) {
				networkFlavour = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Flavour_Healthcare_" + ((i % 10) + 1));
				vertical = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Vertical_Healthcare");
				Traffic_type = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Traffic_type_Transmitting_mostly");
				Typical_data_profile = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Typical_data_profile_Text");
				Device_Type = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Device_Type_Sensors");
				Device_Mobility = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Velocity_" + ((i % 5) + 4));
				if (i % 5 < 3)
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_2");
				else
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_3");
			}
			// Flavour for Gaming
			else if (i < 20) {
				networkFlavour = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Flavour_Gaming_" + ((i % 10) + 1));
				vertical = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Vertical_Gaming");
				Traffic_type = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Traffic_type_same_Receiving_and_transmitting");
				Typical_data_profile = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Typical_data_profile_Text");
				Device_Type = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Device_Type_Smartphones");
				Device_Mobility = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Velocity_" + ((i % 5) + 1));
				Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_" + ((i % 10) + 1));
				if (i % 5 < 3)
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_1");
				else
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_2");
				// Flavour for Public Safety
			} else {
				networkFlavour = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Flavour_Public_Safety_" + ((i % 10) + 1));
				Traffic_type = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Traffic_type_same_Receiving_and_transmitting");
				vertical = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Vertical_Public_Safety");
				Typical_data_profile = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Typical_data_profile_Telematics_data");
				Device_Type = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Device_Type_Smartphones");
				Device_Mobility = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Velocity_" + ((i % 5) + 8));
				Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_" + ((i % 10) + 3));
				if (i % 5 < 3)
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_2");
				else
					Density = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_3");
			}
			OWLClass class_Network_Flavour = dataFactory.getOWLClass(ontologyPrefix + "#Network_Flavour");
			OWLDeclarationAxiom declarationAxiom_NetworkFlavour = dataFactory.getOWLDeclarationAxiom(networkFlavour);
			ontology.add(declarationAxiom_NetworkFlavour);
			OWLClassAssertionAxiom classAssertionAxiom_Network_Flavour = dataFactory
					.getOWLClassAssertionAxiom(class_Network_Flavour, networkFlavour);
			ontology.add(classAssertionAxiom_Network_Flavour);

			int j = i % 10;
			if (j % 5 < 3) {
				Business = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Business_0");
				Operation = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Static");
				Data_Volume_per_device = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Data_Volume_per_device_" + ((j % 5) + 1));
				Latency = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Latency_" + ((j % 5) + 1));

			} else {
				Business = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Business_1");
				Operation = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Dynamic");
				Data_Volume_per_device = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Data_Volume_per_device_4");
				Latency = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Latency_4");
			}
			if (j < 3 || j > 6) {
				Data_security = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Data_security_" + ((j % 7) + 1));
				Energy_efficiency = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Energy_efficiency_" + ((j % 7) + 1));
				Isolation = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Isolation_" + ((j % 7) + 1));
				Reliability = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Reliability_" + ((j % 7) + 1));
			}

			else {
				Data_security = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Data_security_4");
				Energy_efficiency = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Energy_efficiency_3");
				Isolation = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Isolation_3");
				Reliability = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Reliability_" + ((j % 2) + 4));
			}
			Throughput = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Throughput_" + ((j % 5) + 1));

			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Vertical_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, vertical);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Business_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Business);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Data_Security_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Data_security);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Data_Volume_per_device_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour,
							Data_Volume_per_device);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Energy_efficiency_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour,
							Energy_efficiency);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Isolation_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Isolation);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Latency_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Latency);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Reliability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Reliability);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Throughput_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Throughput);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Operation_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Operation);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Traffic_Type_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Traffic_type);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Typical_data_profile_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour,
							Typical_data_profile);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Device_Type_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Device_Type);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Device_Mobility_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour,
							Device_Mobility);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Density_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes, networkFlavour, Density);
			ontology.add(NetworkFlavour_has_Vertical_Axiom);
			ontology.add(NetworkFlavour_has_Business_Axiom);
			ontology.add(NetworkFlavour_has_Data_Security_Axiom);
			ontology.add(NetworkFlavour_has_Data_Volume_per_device_Axiom);
			ontology.add(NetworkFlavour_has_Energy_efficiency_Axiom);
			ontology.add(NetworkFlavour_has_Isolation_Axiom);
			ontology.add(NetworkFlavour_has_Latency_Axiom);
			ontology.add(NetworkFlavour_has_Reliability_Axiom);
			ontology.add(NetworkFlavour_has_Throughput_Axiom);
			ontology.add(NetworkFlavour_has_Operation_Axiom);
			ontology.add(NetworkFlavour_has_Traffic_Type_Axiom);
			ontology.add(NetworkFlavour_has_Typical_data_profile_Axiom);
			ontology.add(NetworkFlavour_has_Device_Type_Axiom);
			ontology.add(NetworkFlavour_has_Device_Mobility_Axiom);
			ontology.add(NetworkFlavour_has_Density_Axiom);

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV		has_Network_Flavour_Attributes_Inv
			OWLObjectProperty has_Network_Flavour_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Flavour_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Vertical_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, vertical, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Business_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Business, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Data_Security_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Data_security,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Data_Volume_per_device_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Data_Volume_per_device,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Energy_efficiency_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Energy_efficiency,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Isolation_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Isolation, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Latency_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Latency, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Reliability_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Reliability,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Throughput_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Throughput, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Operation_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Operation, networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Traffic_Type_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Traffic_type,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Typical_data_profile_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Typical_data_profile,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Device_Type_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Device_Type,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Device_Mobility_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Device_Mobility,
							networkFlavour);
			OWLObjectPropertyAssertionAxiom NetworkFlavour_has_Density_Axiom_inv = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Flavour_Attributes_Inv, Density, networkFlavour);
			ontology.add(NetworkFlavour_has_Vertical_Axiom_inv);
			ontology.add(NetworkFlavour_has_Business_Axiom_inv);
			ontology.add(NetworkFlavour_has_Data_Security_Axiom_inv);
			ontology.add(NetworkFlavour_has_Data_Volume_per_device_Axiom_inv);
			ontology.add(NetworkFlavour_has_Energy_efficiency_Axiom_inv);
			ontology.add(NetworkFlavour_has_Isolation_Axiom_inv);
			ontology.add(NetworkFlavour_has_Latency_Axiom_inv);
			ontology.add(NetworkFlavour_has_Reliability_Axiom_inv);
			ontology.add(NetworkFlavour_has_Throughput_Axiom_inv);
			ontology.add(NetworkFlavour_has_Operation_Axiom_inv);
			ontology.add(NetworkFlavour_has_Traffic_Type_Axiom_inv);
			ontology.add(NetworkFlavour_has_Typical_data_profile_Axiom_inv);
			ontology.add(NetworkFlavour_has_Device_Type_Axiom_inv);
			ontology.add(NetworkFlavour_has_Device_Mobility_Axiom_inv);
			ontology.add(NetworkFlavour_has_Density_Axiom_inv);

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// Match Network Flavour vs Network Slice
			OWLObjectProperty Network_Flavour_require_Network_Slice = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#Network_Flavour_require_Network_Slice");
			OWLObjectProperty Network_Slice_Sastify_Network_Flavour = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#Network_Slice_Sastify_Network_Flavour");
			String NetworkSliceType;
			if (i < 10)
				NetworkSliceType = "Network_Slice_Healthcare_";
			else if (i < 20)
				NetworkSliceType = "Network_Slice_Gaming_";
			else
				NetworkSliceType = "Network_Slice_Public_Safety_";
			OWLNamedIndividual networkSlice;
			if (j < 3)
				networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "1");
			else if (j < 5)
				networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "2");
			else if (j < 7)
				networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "3");
			else
				networkSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#" + NetworkSliceType + "4");
			OWLObjectPropertyAssertionAxiom Network_Flavour_require_Network_Slice_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(Network_Flavour_require_Network_Slice, networkFlavour,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Network_Slice_Sastify_Network_Flavour_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(Network_Slice_Sastify_Network_Flavour, networkSlice,
							networkFlavour);
			ontology.add(Network_Flavour_require_Network_Slice_Axiom);
			ontology.add(Network_Slice_Sastify_Network_Flavour_Axiom);

		}
	}

	private static void generateNetworkSlice() {
		for (int i = 0; i < 4; i++) {
			OWLNamedIndividual networkSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Public_Safety_" + (i + 1));
			OWLClass class_Network_slice = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_Network_Slice = dataFactory
					.getOWLClassAssertionAxiom(class_Network_slice, networkSlice);
			ontology.add(classAssertionAxiom_Network_Slice);
			OWLObjectProperty has_Network_Slice_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes");

			OWLNamedIndividual Delay_Tolerance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Delay_Tolerance_supported");
			OWLNamedIndividual DC_Availability = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#DC_Availability_supported");
			OWLNamedIndividual Group_communication_support = dataFactory.getOWLNamedIndividual(
					ontologyPrefix + "#Group_communication_support_Broadcast/Multicast_+_SC-PTM");
			OWLNamedIndividual Mission_critical_support = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Mission_critical");
			OWLNamedIndividual Mission_critical_capability_support = dataFactory.getOWLNamedIndividual(
					ontologyPrefix + "#Mission-critical_capability_support_Inter_user_prioritization");
			OWLNamedIndividual Mission_critical_service_support1 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#MCPTT");
			OWLNamedIndividual Mission_critical_service_support2 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#MCData");
			OWLNamedIndividual Mission_critical_service_support3 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#MCVideo");
			OWLNamedIndividual MMTel_support = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MMTel_supported");
			OWLNamedIndividual PS_Availability_1 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_AECID");
			OWLNamedIndividual PS_Availability_2 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_OTDOA");
			OWLNamedIndividual PS_Availability_3 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#PS_Availability_CIDE-CID");
			OWLNamedIndividual PS_Prediction_frequency = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#PS_Prediction_frequency_Per_minute");
			OWLNamedIndividual SCS_mode = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#SCS_mode1");
			OWLNamedIndividual Support_for_Non_IP_traffic = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Support_for_non-IP_traffic_supported");
			OWLNamedIndividual Synchronicity_Availability = dataFactory.getOWLNamedIndividual(
					ontologyPrefix + "#Synchronicity_Availability_Between_BS_and_UE_&_UE_and_UE");
			OWLNamedIndividual User_Management_Openness = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#User_management_openness_Supported");
			OWLNamedIndividual V2X_communication_mode = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#V2X_communication_mode_YES_NR_and_E_UTRA");
			OWLNamedIndividual MPS = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MPS");
			OWLNamedIndividual GPP_5QI = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_2");
			;
			OWLNamedIndividual Availability;

			OWLNamedIndividual Guaranteed_downlink;
			OWLNamedIndividual Guaranteed_uplink;
			OWLNamedIndividual Max_downlink;
			OWLNamedIndividual Max_uplink;
			OWLNamedIndividual Max_downlink_per_UE;
			OWLNamedIndividual Max_uplink_per_UE;

			OWLNamedIndividual Velocity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_3");
			;

			if (i == 1 || i == 2)
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_3");
			else
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_2");
			if (i % 2 == 0) {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_3");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_3");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_3");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_3");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_3");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_3");

			} else {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_4");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_4");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_4");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_4");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_4");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_4");
			}
			;

			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Delay_Tolerance);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_DC_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, DC_Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Group_communication_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Group_communication_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_capability_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_capability_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support1_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_service_support1);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_service_support2);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support3_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_service_support3);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_MMTel_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MMTel_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_1_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_1);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_2);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Availability_3_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, PS_Availability_3);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_PS_Prediction_frequency_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							PS_Prediction_frequency);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_SCS_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, SCS_mode);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Support_for_Non_IP_traffic_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Support_for_Non_IP_traffic);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Synchronicity_Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_User_Management_Openness_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							User_Management_Openness);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							V2X_communication_mode);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MPS);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, GPP_5QI);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Guaranteed_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Max_downlink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Velocity);

			ontology.add(NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(NetworkSlice_has_DC_Availability_Axiom);
			ontology.add(NetworkSlice_has_Group_communication_support_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_support_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_capability_support_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_service_support1_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_service_support2_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_service_support3_Axiom);
			ontology.add(NetworkSlice_has_MMTel_support_Axiom);
			ontology.add(NetworkSlice_has_PS_Availability_1_Axiom);
			ontology.add(NetworkSlice_has_PS_Availability_2_Axiom);
			ontology.add(NetworkSlice_has_PS_Availability_3_Axiom);
			ontology.add(NetworkSlice_has_PS_Prediction_frequency_Axiom);
			ontology.add(NetworkSlice_has_SCS_mode_Axiom);
			ontology.add(NetworkSlice_has_Support_for_Non_IP_traffic_Axiom);
			ontology.add(NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(NetworkSlice_has_User_Management_Openness_Axiom);
			ontology.add(NetworkSlice_has_V2X_communication_mode_Axiom);
			ontology.add(NetworkSlice_has_MPS_Axiom);
			ontology.add(NetworkSlice_has_Availability_Axiom);
			ontology.add(NetworkSlice_has_throughput_Axiom_1);
			ontology.add(NetworkSlice_has_throughput_Axiom_2);
			ontology.add(NetworkSlice_has_throughput_Axiom_3);
			ontology.add(NetworkSlice_has_throughput_Axiom_4);
			ontology.add(NetworkSlice_has_throughput_Axiom_5);
			ontology.add(NetworkSlice_has_throughput_Axiom_6);
			ontology.add(NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
//INV		has_Network_Slice_Attributes_Inv
			OWLObjectProperty has_Network_Slice_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Delay_Tolerance,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_DC_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, DC_Availability,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Group_communication_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Group_communication_support,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Mission_critical_support,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_capability_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv,
							Mission_critical_capability_support, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_service_support1_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv,
							Mission_critical_service_support1, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv,
							Mission_critical_service_support2, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_service_support3_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv,
							Mission_critical_service_support3, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_MMTel_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, MMTel_support, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_PS_Availability_1_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, PS_Availability_1,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_PS_Availability_2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, PS_Availability_2,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_PS_Availability_3_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, PS_Availability_3,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_PS_Prediction_frequency_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, PS_Prediction_frequency,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_SCS_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, SCS_mode, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Support_for_Non_IP_traffic_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Support_for_Non_IP_traffic,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Synchronicity_Availability,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_User_Management_Openness_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, User_Management_Openness,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, V2X_communication_mode,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, MPS, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, GPP_5QI, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Availability, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_downlink,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_uplink,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_uplink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink_per_UE,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_uplink_per_UE,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Velocity, networkSlice);

			ontology.add(Inv_NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(Inv_NetworkSlice_has_DC_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_Group_communication_support_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_support_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_capability_support_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_service_support1_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_service_support2_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_service_support3_Axiom);
			ontology.add(Inv_NetworkSlice_has_MMTel_support_Axiom);
			ontology.add(Inv_NetworkSlice_has_PS_Availability_1_Axiom);
			ontology.add(Inv_NetworkSlice_has_PS_Availability_2_Axiom);
			ontology.add(Inv_NetworkSlice_has_PS_Availability_3_Axiom);
			ontology.add(Inv_NetworkSlice_has_PS_Prediction_frequency_Axiom);
			ontology.add(Inv_NetworkSlice_has_SCS_mode_Axiom);
			ontology.add(Inv_NetworkSlice_has_Support_for_Non_IP_traffic_Axiom);
			ontology.add(Inv_NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_User_Management_Openness_Axiom);
			ontology.add(Inv_NetworkSlice_has_V2X_communication_mode_Axiom);
			ontology.add(Inv_NetworkSlice_has_MPS_Axiom);
			ontology.add(Inv_NetworkSlice_has_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_1);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_2);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_3);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_4);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_5);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_6);
			ontology.add(Inv_NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(Inv_NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		}

		for (int i = 0; i < 4; i++) {
			OWLNamedIndividual networkSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Gaming_" + (i + 1));
			OWLClass class_Network_slice = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_Network_Slice = dataFactory
					.getOWLClassAssertionAxiom(class_Network_slice, networkSlice);
			ontology.add(classAssertionAxiom_Network_Slice);
			OWLObjectProperty has_Network_Slice_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes");

			OWLNamedIndividual Delay_Tolerance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Delay_Tolerance_supported");
			OWLNamedIndividual SCS_mode = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#SCS_mode1");
			OWLNamedIndividual Support_for_Non_IP_traffic = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Support_for_non-IP_traffic_supported");
			OWLNamedIndividual Synchronicity_Availability = dataFactory.getOWLNamedIndividual(
					ontologyPrefix + "#Synchronicity_Availability_Between_BS_and_UE_&_UE_and_UE");
			OWLNamedIndividual MPS = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MPS");
			OWLNamedIndividual GPP_5QI = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_1");
			;
			OWLNamedIndividual Availability;

			OWLNamedIndividual Guaranteed_downlink;
			OWLNamedIndividual Guaranteed_uplink;
			OWLNamedIndividual Max_downlink;
			OWLNamedIndividual Max_uplink;
			OWLNamedIndividual Max_downlink_per_UE;
			OWLNamedIndividual Max_uplink_per_UE;

			OWLNamedIndividual Velocity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_1");
			;

			if (i == 1 || i == 2)
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_2");
			else
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_1");
			if (i % 2 == 0) {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_2");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_2");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_2");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_2");

			} else {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_4");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_2");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_2");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_2");
			}
			;

			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Delay_Tolerance);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_SCS_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, SCS_mode);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Support_for_Non_IP_traffic_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Support_for_Non_IP_traffic);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Synchronicity_Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MPS);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, GPP_5QI);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Guaranteed_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Max_downlink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Velocity);
			ontology.add(NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(NetworkSlice_has_SCS_mode_Axiom);
			ontology.add(NetworkSlice_has_Support_for_Non_IP_traffic_Axiom);
			ontology.add(NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(NetworkSlice_has_MPS_Axiom);
			ontology.add(NetworkSlice_has_Availability_Axiom);
			ontology.add(NetworkSlice_has_throughput_Axiom_1);
			ontology.add(NetworkSlice_has_throughput_Axiom_2);
			ontology.add(NetworkSlice_has_throughput_Axiom_3);
			ontology.add(NetworkSlice_has_throughput_Axiom_4);
			ontology.add(NetworkSlice_has_throughput_Axiom_5);
			ontology.add(NetworkSlice_has_throughput_Axiom_6);
			ontology.add(NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
//INV		has_Network_Slice_Attributes_Inv
			OWLObjectProperty has_Network_Slice_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Delay_Tolerance,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_SCS_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, SCS_mode, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Support_for_Non_IP_traffic_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Support_for_Non_IP_traffic,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Synchronicity_Availability,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, MPS, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, GPP_5QI, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Availability, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_downlink,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_uplink,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_uplink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink_per_UE,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_uplink_per_UE,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Velocity, networkSlice);
			ontology.add(Inv_NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(Inv_NetworkSlice_has_SCS_mode_Axiom);
			ontology.add(Inv_NetworkSlice_has_Support_for_Non_IP_traffic_Axiom);
			ontology.add(Inv_NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_MPS_Axiom);
			ontology.add(Inv_NetworkSlice_has_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_1);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_2);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_3);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_4);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_5);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_6);
			ontology.add(Inv_NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(Inv_NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		}

		for (int i = 0; i < 4; i++) {
			OWLNamedIndividual networkSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Healthcare_" + (i + 1));
			OWLClass class_Network_slice = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_Network_Slice = dataFactory
					.getOWLClassAssertionAxiom(class_Network_slice, networkSlice);
			ontology.add(classAssertionAxiom_Network_Slice);
			OWLObjectProperty has_Network_Slice_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes");

			OWLNamedIndividual Delay_Tolerance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Delay_Tolerance_supported");
			;
			OWLNamedIndividual Mission_critical_support = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Mission_critical");
			OWLNamedIndividual Mission_critical_service_support2 = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#MCData");
			OWLNamedIndividual Synchronicity_Availability = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Synchronicity_Availability_Between_BS_and_UE");
			OWLNamedIndividual V2X_communication_mode = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#V2X_communication_mode_YES_NR_and_E_UTRA");
			OWLNamedIndividual MPS = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#MPS");
			OWLNamedIndividual GPP_5QI = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_2");
			;
			OWLNamedIndividual Availability;

			OWLNamedIndividual Guaranteed_downlink;
			OWLNamedIndividual Guaranteed_uplink;
			OWLNamedIndividual Max_downlink;
			OWLNamedIndividual Max_uplink;
			OWLNamedIndividual Max_downlink_per_UE;
			OWLNamedIndividual Max_uplink_per_UE;

			OWLNamedIndividual Velocity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_2");
			;

			if (i == 1 || i == 2)
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_3");
			else
				Availability = dataFactory
						.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_2");
			if (i % 2 == 0) {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_1");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_1");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_1");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_1");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_1");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_1");

			} else {
				Guaranteed_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Guaranteed_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_2");
				Max_downlink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_2");
				Max_uplink = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_2");
				Max_downlink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_2");
				Max_uplink_per_UE = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_2");
			}
			;

			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Delay_Tolerance);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_support);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Mission_critical_service_support2);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Synchronicity_Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							V2X_communication_mode);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, MPS);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, GPP_5QI);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Availability);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Guaranteed_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Guaranteed_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_downlink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice,
							Max_downlink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Max_uplink_per_UE);
			OWLObjectPropertyAssertionAxiom NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, networkSlice, Velocity);

			ontology.add(NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_support_Axiom);
			ontology.add(NetworkSlice_has_Mission_critical_service_support2_Axiom);
			ontology.add(NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(NetworkSlice_has_V2X_communication_mode_Axiom);
			ontology.add(NetworkSlice_has_MPS_Axiom);
			ontology.add(NetworkSlice_has_Availability_Axiom);
			ontology.add(NetworkSlice_has_throughput_Axiom_1);
			ontology.add(NetworkSlice_has_throughput_Axiom_2);
			ontology.add(NetworkSlice_has_throughput_Axiom_3);
			ontology.add(NetworkSlice_has_throughput_Axiom_4);
			ontology.add(NetworkSlice_has_throughput_Axiom_5);
			ontology.add(NetworkSlice_has_throughput_Axiom_6);
			ontology.add(NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV		has_Network_Slice_Attributes_Inv
			OWLObjectProperty has_Network_Slice_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Network_Slice_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Delay_Tolerance_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Delay_Tolerance, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_support_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Mission_critical_support,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Mission_critical_service_support2_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Mission_critical_service_support2,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Synchronicity_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Synchronicity_Availability,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_V2X_communication_mode_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, V2X_communication_mode,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_MPS_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, MPS, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_3GPP_5QI_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, GPP_5QI, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Availability_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Availability, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_1 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_downlink,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_2 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Guaranteed_uplink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_3 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_4 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_uplink, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_5 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes_Inv, Max_downlink_per_UE,
							networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_throughput_Axiom_6 = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, Max_uplink_per_UE, networkSlice);
			OWLObjectPropertyAssertionAxiom Inv_NetworkSlice_has_Velocity = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Network_Slice_Attributes, Velocity, networkSlice);

			ontology.add(Inv_NetworkSlice_has_Delay_Tolerance_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_support_Axiom);
			ontology.add(Inv_NetworkSlice_has_Mission_critical_service_support2_Axiom);
			ontology.add(Inv_NetworkSlice_has_Synchronicity_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_V2X_communication_mode_Axiom);
			ontology.add(Inv_NetworkSlice_has_MPS_Axiom);
			ontology.add(Inv_NetworkSlice_has_Availability_Axiom);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_1);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_2);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_3);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_4);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_5);
			ontology.add(Inv_NetworkSlice_has_throughput_Axiom_6);
			ontology.add(Inv_NetworkSlice_has_3GPP_5QI_Axiom);
			ontology.add(Inv_NetworkSlice_has_Velocity);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
	}

	private static void generateCloudFlavour() {
		// 5 Flavour from small size to big size
		for (int i = 0; i < 5; i++) {
			OWLNamedIndividual cloudFlavour = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Flavour_" + (i + 1));
			OWLClass class_Cloud_flavour = dataFactory.getOWLClass(ontologyPrefix + "#Cloud_Flavour");
			OWLClassAssertionAxiom classAssertionAxiom_Cloud_Flavour = dataFactory
					.getOWLClassAssertionAxiom(class_Cloud_flavour, cloudFlavour);
			ontology.add(classAssertionAxiom_Cloud_Flavour);
			OWLObjectProperty has_Cloud_Flavour_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Flavour_Attributes");
			OWLNamedIndividual CPU_Requirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#CPU_Requirement_" + (i + 1));
			OWLNamedIndividual RAM_Requirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#RAM_Requirement_" + (i + 1));
			OWLNamedIndividual Storage_Requirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Storage_Requirement_" + (i + 1));
			OWLObjectPropertyAssertionAxiom CloudFlavour_has_CPU_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes, cloudFlavour, CPU_Requirement);
			OWLObjectPropertyAssertionAxiom CloudFlavour_has_RAM_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes, cloudFlavour, RAM_Requirement);
			OWLObjectPropertyAssertionAxiom CloudFlavour_has_Storage_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes, cloudFlavour,
							Storage_Requirement);
			ontology.add(CloudFlavour_has_CPU_Axiom);
			ontology.add(CloudFlavour_has_RAM_Axiom);
			ontology.add(CloudFlavour_has_Storage_Axiom);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INV		has_Cloud_Flavour_Attributes_Inv
			OWLObjectProperty has_Cloud_Flavour_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Flavour_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom Inv_CloudFlavour_has_CPU_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes_Inv, CPU_Requirement,
							cloudFlavour);
			OWLObjectPropertyAssertionAxiom Inv_CloudFlavour_has_RAM_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes_Inv, RAM_Requirement,
							cloudFlavour);
			OWLObjectPropertyAssertionAxiom Inv_CloudFlavour_has_Storage_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Flavour_Attributes_Inv, Storage_Requirement,
							cloudFlavour);
			ontology.add(Inv_CloudFlavour_has_CPU_Axiom);
			ontology.add(Inv_CloudFlavour_has_RAM_Axiom);
			ontology.add(Inv_CloudFlavour_has_Storage_Axiom);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
	}

	private static void generateCloudSlice() {
		// 5 Slice apply to 5 Flavour
		for (int i = 0; i < 5; i++) {
			OWLNamedIndividual cloudSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Slice_" + (i + 1));
			OWLClass class_Cloud_slice = dataFactory.getOWLClass(ontologyPrefix + "#Cloud_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_Cloud_Slice = dataFactory
					.getOWLClassAssertionAxiom(class_Cloud_slice, cloudSlice);
			ontology.add(classAssertionAxiom_Cloud_Slice);
			OWLObjectProperty has_Cloud_Slice_Attributes = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Slice_Attributes");
			OWLNamedIndividual CPU_Slice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#CPU_Slice_" + (i + 1));
			OWLNamedIndividual RAM_Slice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#RAM_Slice_" + (i + 1));
			OWLNamedIndividual Storage_Slice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Storage_Slice_" + (i + 1));
			OWLObjectPropertyAssertionAxiom CloudSlice_has_CPU_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes, cloudSlice, CPU_Slice);
			OWLObjectPropertyAssertionAxiom CloudSlice_has_RAM_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes, cloudSlice, RAM_Slice);
			OWLObjectPropertyAssertionAxiom CloudSlice_has_Storage_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes, cloudSlice, Storage_Slice);
			ontology.add(CloudSlice_has_CPU_Axiom);
			ontology.add(CloudSlice_has_RAM_Axiom);
			ontology.add(CloudSlice_has_Storage_Axiom);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// INV has_Cloud_Slice_Attributes_Inv
			OWLObjectProperty has_Cloud_Slice_Attributes_Inv = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#has_Cloud_Slice_Attributes_Inv");
			OWLObjectPropertyAssertionAxiom Inv_CloudSlice_has_CPU_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes_Inv, CPU_Slice, cloudSlice);
			OWLObjectPropertyAssertionAxiom Inv_CloudSlice_has_RAM_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes_Inv, RAM_Slice, cloudSlice);
			OWLObjectPropertyAssertionAxiom Inv_CloudSlice_has_Storage_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(has_Cloud_Slice_Attributes_Inv, Storage_Slice, cloudSlice);
			ontology.add(Inv_CloudSlice_has_CPU_Axiom);
			ontology.add(Inv_CloudSlice_has_RAM_Axiom);
			ontology.add(Inv_CloudSlice_has_Storage_Axiom);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}

	}

	private static void Matching_Cloud_Slice() {
		// Match UserApp and Cloud_Slice
		for (int i = 0; i < 5; i++) {
			OWLObjectProperty Cloud_Flavour_require_Cloud_Slice = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#Cloud_Flavour_require_Cloud_Slice");
			OWLObjectProperty Cloud_Slice_Sastify_Cloud_Flavour = dataFactory
					.getOWLObjectProperty(ontologyPrefix + "#Cloud_Slice_Sastify_Cloud_Flavour");
			OWLNamedIndividual cloudSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Slice_" + (i + 1));
			OWLNamedIndividual cloudFlavour = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Cloud_Flavour_" + (i + 1));
			OWLObjectPropertyAssertionAxiom Cloud_Flavour_require_Cloud_Slice_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(Cloud_Flavour_require_Cloud_Slice, cloudFlavour, cloudSlice);
			OWLObjectPropertyAssertionAxiom Cloud_Slice_Sastify_Cloud_Flavour_Axiom = dataFactory
					.getOWLObjectPropertyAssertionAxiom(Cloud_Slice_Sastify_Cloud_Flavour, cloudSlice, cloudFlavour);
			ontology.add(Cloud_Flavour_require_Cloud_Slice_Axiom);
			ontology.add(Cloud_Slice_Sastify_Cloud_Flavour_Axiom);
		}
	}

/////////////////////////////data properties
	private static void generateDataProperties() {
		// Flavour Device Mobility
		OWLDataProperty has_Network_Flavour_Device_Mobility = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Flavour_Device_Mobility");
		for (int i = 0; i < velocityValues.length; i++) {
			int velocityValue = velocityValues[i];
			OWLNamedIndividual velocity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Velocity_" + (i + 1));
			OWLClass class_Velocity = dataFactory.getOWLClass(ontologyPrefix + "#Device_Mobility");
			OWLDeclarationAxiom declarationAxiom_Velocity = dataFactory.getOWLDeclarationAxiom(class_Velocity);
			ontology.add(declarationAxiom_Velocity);
			OWLClassAssertionAxiom classAssertionAxiom_Velocity = dataFactory.getOWLClassAssertionAxiom(class_Velocity,
					velocity);
			ontology.add(classAssertionAxiom_Velocity);
			OWLDataPropertyAssertionAxiom VelocityAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Flavour_Device_Mobility, velocity, velocityValue);
			ontology.add(VelocityAttrDataPropAssertionAxiom);
		}
		// Flavour Density
		OWLDataProperty has_Network_Flavour_Density = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Flavour_Density");
		for (int i = 0; i < userDensityValues.length; i++) {
			int userDensityValue = userDensityValues[i];
			OWLNamedIndividual userDensity = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Flavour_Density_" + (i + 1));
			OWLClass class_UserDensity = dataFactory.getOWLClass(ontologyPrefix + "#Flavour_Density");
			OWLClassAssertionAxiom classAssertionAxiom_UserDensity = dataFactory
					.getOWLClassAssertionAxiom(class_UserDensity, userDensity);
			ontology.add(classAssertionAxiom_UserDensity);
			OWLDataPropertyAssertionAxiom DensityAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Flavour_Density, userDensity, userDensityValue);
			ontology.add(DensityAttrDataPropAssertionAxiom);
		}
		// CPU
		OWLDataProperty has_Cloud_Flavour_CPU = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Flavour_CPU");
		OWLDataProperty has_Cloud_Slice_CPU = dataFactory.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Slice_CPU");
		for (int i = 0; i < CPUValues.length; i++) {
			int CPU = CPUValues[i];
			OWLNamedIndividual CPUrequirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#CPU_Requirement_" + (i + 1));
			OWLClass class_FlavourCPU = dataFactory.getOWLClass(ontologyPrefix + "#Flavour_CPU");
			OWLClassAssertionAxiom classAssertionAxiom_FlavourCPU = dataFactory
					.getOWLClassAssertionAxiom(class_FlavourCPU, CPUrequirement);
			ontology.add(classAssertionAxiom_FlavourCPU);
			OWLDataPropertyAssertionAxiom CPURequirementAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Flavour_CPU, CPUrequirement, CPU);
			ontology.add(CPURequirementAttrDataPropAssertionAxiom);

			OWLNamedIndividual CPUSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#CPU_Slice_" + (i + 1));
			OWLClass class_SliceCPU = dataFactory.getOWLClass(ontologyPrefix + "#CPU_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_SliceCPU = dataFactory.getOWLClassAssertionAxiom(class_SliceCPU,
					CPUSlice);
			ontology.add(classAssertionAxiom_SliceCPU);
			OWLDataPropertyAssertionAxiom CPUSliceAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Slice_CPU, CPUSlice, CPU);
			ontology.add(CPUSliceAttrDataPropAssertionAxiom);
		}
		// RAM
		OWLDataProperty has_Cloud_Flavour_RAM = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Flavour_RAM");
		OWLDataProperty has_Cloud_Slice_RAM = dataFactory.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Slice_RAM");
		for (int i = 0; i < RAMValues.length; i++) {
			int RAM = RAMValues[i];
			OWLNamedIndividual RAMrequirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#RAM_Requirement_" + (i + 1));
			OWLClass class_FlavourRAM = dataFactory.getOWLClass(ontologyPrefix + "#Flavour_RAM");
			OWLClassAssertionAxiom classAssertionAxiom_FlavourRAM = dataFactory
					.getOWLClassAssertionAxiom(class_FlavourRAM, RAMrequirement);
			ontology.add(classAssertionAxiom_FlavourRAM);
			OWLDataPropertyAssertionAxiom RAMRequirementAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Flavour_RAM, RAMrequirement, RAM);
			ontology.add(RAMRequirementAttrDataPropAssertionAxiom);

			OWLNamedIndividual RAMSlice = dataFactory.getOWLNamedIndividual(ontologyPrefix + "#RAM_Slice_" + (i + 1));
			OWLClass class_SliceRAM = dataFactory.getOWLClass(ontologyPrefix + "#RAM_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_SliceRAM = dataFactory.getOWLClassAssertionAxiom(class_SliceRAM,
					RAMSlice);
			ontology.add(classAssertionAxiom_SliceRAM);
			OWLDataPropertyAssertionAxiom RAMSliceAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Slice_RAM, RAMSlice, RAM);
			ontology.add(RAMSliceAttrDataPropAssertionAxiom);
		}

		// Storage
		OWLDataProperty has_Cloud_Flavour_Storage = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Flavour_Storage");
		OWLDataProperty has_Cloud_Slice_Storage = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Cloud_Slice_Storage");
		for (int i = 0; i < StorageValues.length; i++) {
			int Storage = StorageValues[i];
			OWLNamedIndividual Storagerequirement = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Storage_Requirement_" + (i + 1));
			OWLClass class_FlavourStorage = dataFactory.getOWLClass(ontologyPrefix + "#Flavour_Storage");
			OWLClassAssertionAxiom classAssertionAxiom_FlavourStorage = dataFactory
					.getOWLClassAssertionAxiom(class_FlavourStorage, Storagerequirement);
			ontology.add(classAssertionAxiom_FlavourStorage);
			OWLDataPropertyAssertionAxiom StorageRequirementAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Flavour_Storage, Storagerequirement, Storage);
			ontology.add(StorageRequirementAttrDataPropAssertionAxiom);

			OWLNamedIndividual StorageSlice = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Storage_Slice_" + (i + 1));
			OWLClass class_SliceStorage = dataFactory.getOWLClass(ontologyPrefix + "#Storage_Slice");
			OWLClassAssertionAxiom classAssertionAxiom_SliceStorage = dataFactory
					.getOWLClassAssertionAxiom(class_SliceStorage, StorageSlice);
			ontology.add(classAssertionAxiom_SliceStorage);
			OWLDataPropertyAssertionAxiom StorageSliceAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Cloud_Slice_Storage, StorageSlice, Storage);
			ontology.add(StorageSliceAttrDataPropAssertionAxiom);
		}
		// Network Slice

		// Availability
		OWLDataProperty has_Network_Slice_Availability = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Availability");
		for (int i = 0; i < Network_Slice_Availability.length; i++) {
			double Network_Slice_Availability_Value = Network_Slice_Availability[i];
			OWLNamedIndividual Network_Slice_Availability_Instance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Network_Slice_Availability_Instance_" + (i + 1));
			OWLClass class_Availability = dataFactory.getOWLClass(ontologyPrefix + "#Availability");
			OWLClassAssertionAxiom classAssertionAxiom_Availability = dataFactory
					.getOWLClassAssertionAxiom(class_Availability, Network_Slice_Availability_Instance);
			ontology.add(classAssertionAxiom_Availability);
			OWLDataPropertyAssertionAxiom AvailabilityAttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Slice_Availability,
							Network_Slice_Availability_Instance, Network_Slice_Availability_Value);
			ontology.add(AvailabilityAttrDataPropAssertionAxiom);
		}
		// 3GPP
		OWLDataProperty has_Network_Slice_3GPP = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_3GPP_5QI");
		for (int i = 0; i < GPP_5QI.length; i++) {
			int GPP_5QI_Value = GPP_5QI[i];
			OWLNamedIndividual GPP_5QI_Instance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#3GPP_5QI_Instance_" + (i + 1));
			OWLClass class_GPP_5QI = dataFactory.getOWLClass(ontologyPrefix + "#3GPP_5QI");
			OWLClassAssertionAxiom classAssertionAxiom = dataFactory.getOWLClassAssertionAxiom(class_GPP_5QI,
					GPP_5QI_Instance);
			ontology.add(classAssertionAxiom);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Slice_3GPP, GPP_5QI_Instance, GPP_5QI_Value);
			ontology.add(AttrDataPropAssertionAxiom);
		}

		// bandwidth
		OWLDataProperty has_Network_Slice_Guaranteed_downlink = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Guaranteed_downlink");
		OWLDataProperty has_Network_Slice_Guaranteed_uplink = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Guaranteed_uplink");
		OWLDataProperty has_Network_Slice_Maximum_uplink = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Maximum_uplink");
		OWLDataProperty has_Network_Slice_Maximum_downlink = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Maximum_downlink");
		OWLDataProperty has_Network_Slice_Maximum_uplink_per_UE = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Maximum_uplink_per_UE");
		OWLDataProperty has_Network_Slice_Maximum_downlink_per_UE = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Maximum_downlink_per_UE");
		for (int i = 0; i < bandwidthValues.length; i++) {
			int bandwidthValue = bandwidthValues[i];
			OWLNamedIndividual Guaranteed_downlink = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_downlink_" + (i + 1));
			OWLNamedIndividual Guaranteed_uplink = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Guaranteed_uplink_" + (i + 1));
			OWLNamedIndividual Max_downlink = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_" + (i + 1));
			OWLNamedIndividual Max_uplink = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_" + (i + 1));
			OWLNamedIndividual Max_downlink_per_UE = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Max_downlink_per_UE_" + (i + 1));
			OWLNamedIndividual Max_uplink_per_UE = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Max_uplink_per_UE_" + (i + 1));
			OWLClass class_Guaranteed_downlink_throughput_quota = dataFactory
					.getOWLClass(ontologyPrefix + "#Guaranteed_downlink_throughput_quota");
			OWLClass class_Guaranteed_uplink_throughput_quota = dataFactory
					.getOWLClass(ontologyPrefix + "#Guaranteed_uplink_throughput_quota");
			OWLClass class_Maximum_uplink_throughput = dataFactory
					.getOWLClass(ontologyPrefix + "#Maximum_uplink_throughput");
			OWLClass class_Maximum_downlink_throughput = dataFactory
					.getOWLClass(ontologyPrefix + "#Maximum_downlink_throughput");
			OWLClass class_Maximum_uplink_throughput_per_UE = dataFactory
					.getOWLClass(ontologyPrefix + "#Maximum_uplink_throughput_per_UE");
			OWLClass class_Maximum_downlink_throughput_per_UE = dataFactory
					.getOWLClass(ontologyPrefix + "#Maximum_downlink_throughput_per_UE");
			OWLClassAssertionAxiom classAssertionAxiom1 = dataFactory
					.getOWLClassAssertionAxiom(class_Guaranteed_downlink_throughput_quota, Guaranteed_downlink);
			OWLClassAssertionAxiom classAssertionAxiom2 = dataFactory
					.getOWLClassAssertionAxiom(class_Guaranteed_uplink_throughput_quota, Guaranteed_uplink);
			OWLClassAssertionAxiom classAssertionAxiom3 = dataFactory
					.getOWLClassAssertionAxiom(class_Maximum_downlink_throughput, Max_downlink);
			OWLClassAssertionAxiom classAssertionAxiom4 = dataFactory
					.getOWLClassAssertionAxiom(class_Maximum_uplink_throughput, Max_uplink);
			OWLClassAssertionAxiom classAssertionAxiom5 = dataFactory
					.getOWLClassAssertionAxiom(class_Maximum_downlink_throughput_per_UE, Max_downlink_per_UE);
			OWLClassAssertionAxiom classAssertionAxiom6 = dataFactory
					.getOWLClassAssertionAxiom(class_Maximum_uplink_throughput_per_UE, Max_uplink_per_UE);
			ontology.add(classAssertionAxiom1);
			ontology.add(classAssertionAxiom2);
			ontology.add(classAssertionAxiom3);
			ontology.add(classAssertionAxiom4);
			ontology.add(classAssertionAxiom5);
			ontology.add(classAssertionAxiom6);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom1 = dataFactory.getOWLDataPropertyAssertionAxiom(
					has_Network_Slice_Guaranteed_downlink, Guaranteed_downlink, bandwidthValue);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom2 = dataFactory.getOWLDataPropertyAssertionAxiom(
					has_Network_Slice_Guaranteed_uplink, Guaranteed_uplink, bandwidthValue);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom3 = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Slice_Maximum_uplink, Max_uplink, bandwidthValue);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom4 = dataFactory
					.getOWLDataPropertyAssertionAxiom(has_Network_Slice_Maximum_downlink, Max_downlink, bandwidthValue);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom5 = dataFactory.getOWLDataPropertyAssertionAxiom(
					has_Network_Slice_Maximum_uplink_per_UE, Max_uplink_per_UE, bandwidthValue);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom6 = dataFactory.getOWLDataPropertyAssertionAxiom(
					has_Network_Slice_Maximum_downlink_per_UE, Max_downlink_per_UE, bandwidthValue);
			ontology.add(AttrDataPropAssertionAxiom1);
			ontology.add(AttrDataPropAssertionAxiom2);
			ontology.add(AttrDataPropAssertionAxiom3);
			ontology.add(AttrDataPropAssertionAxiom4);
			ontology.add(AttrDataPropAssertionAxiom5);
			ontology.add(AttrDataPropAssertionAxiom6);
		}
		// Velocity
		OWLDataProperty has_Network_Slice_Supported_device_velocity = dataFactory
				.getOWLDataProperty(ontologyPrefix + "#has_Network_Slice_Supported_device_velocity");
		for (int i = 0; i < velocitySliceValues.length; i++) {
			int velocitySliceValue = velocitySliceValues[i];
			OWLNamedIndividual Velocity_Slice_Instance = dataFactory
					.getOWLNamedIndividual(ontologyPrefix + "#Velocity_Slice_Instance_" + (i + 1));
			OWLClass class_Velocity_Slice = dataFactory.getOWLClass(ontologyPrefix + "#Supported_device_velocity");
			OWLClassAssertionAxiom classAssertionAxiom = dataFactory.getOWLClassAssertionAxiom(class_Velocity_Slice,
					Velocity_Slice_Instance);
			ontology.add(classAssertionAxiom);
			OWLDataPropertyAssertionAxiom AttrDataPropAssertionAxiom = dataFactory.getOWLDataPropertyAssertionAxiom(
					has_Network_Slice_Supported_device_velocity, Velocity_Slice_Instance, velocitySliceValue);
			ontology.add(AttrDataPropAssertionAxiom);
		}

	}

	private static void printData(int fileIdentifier) throws IOException {
		String baseDirString = System.getProperty("user.dir");
		FileWriter resultWriter = new FileWriter(
				baseDirString + File.separator + "src" + File.separator + "main" + File.separator + "resources"
						+ File.separator + "results" + File.separator + "result" + fileIdentifier + ".txt");
		String[] sensorObjectProperties = { new String("from_App"), new String("UserApp_require_Attributes"),
				new String("has_App_Instance"), new String("UserApp_require_App_Instance"),
				new String("has_Network_Flavour"), new String("has_Network_Flavour_Attributes"),
				new String("has_Cloud_Flavour"), new String("has_Cloud_Flavour_Attributes"),
				new String("has_Cloud_Slice"), new String("has_Cloud_Slice_Attributes"),
				new String("has_Network_Slice"), new String("has_Network_Slice_Attributes"),
				new String("Cloud_Flavour_require_Cloud_Slice"), new String("UserApp_require_Cloud_Slice"),
				new String("Network_Flavour_require_Network_Slice"), new String("UserApp_require_Network_Slice"),
				new String("Network_Slice_Sastify_UserApp"), new String("Network_Slice_Sastify_Network_Flavour"),
				new String("Cloud_Slice_Sastify_Cloud_Flavour"), new String("Cloud_Slice_Sastify_UserApp"),
				new String("App_Instance_Sastify_UserApp"), new String("has_Network_Flavour_Inv"),
				new String("has_Network_Flavour_Attributes_Inv"), new String("has_Cloud_Flavour_Inv"),
				new String("has_Cloud_Flavour_Attributes_Inv"), new String("has_Cloud_Slice_Inv"),
				new String("has_Cloud_Slice_Attributes_Inv"), new String("has_Network_Slice_Inv"),
				new String("has_Network_Slice_Attributes_Inv"), new String("from_App_Inv"),
				new String("UserApp_require_Attributes_Inv"), new String("has_App_Instance_Inv"),

		};

		OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
		OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);

		OWLClass appClass = dataFactory.getOWLClass(ontologyPrefix + "#App");
		NodeSet<OWLNamedIndividual> appClassIndividuals = r.getInstances(appClass, false);
		System.out.println(appClassIndividuals.entities().count());

		OWLClass userappClass = dataFactory.getOWLClass(ontologyPrefix + "#UserApp");
		NodeSet<OWLNamedIndividual> userappClassIndividuals = r.getInstances(userappClass, false);
		System.out.println(userappClassIndividuals.entities().count());

		OWLClass appinstanceClass = dataFactory.getOWLClass(ontologyPrefix + "#App_Instance");
		NodeSet<OWLNamedIndividual> appinstanceClassIndividuals = r.getInstances(appinstanceClass, false);
		System.out.println(appinstanceClassIndividuals.entities().count());

		OWLClass networkflavourClass = dataFactory.getOWLClass(ontologyPrefix + "#Network_Flavour");
		NodeSet<OWLNamedIndividual> networkflavourClassIndividuals = r.getInstances(networkflavourClass, false);
		System.out.println(networkflavourClassIndividuals.entities().count());

		OWLClass cloudflavourClass = dataFactory.getOWLClass(ontologyPrefix + "#Cloud_Flavour");
		NodeSet<OWLNamedIndividual> cloudflavourClassIndividuals = r.getInstances(cloudflavourClass, false);
		System.out.println(cloudflavourClassIndividuals.entities().count());

		OWLClass networksliceClass = dataFactory.getOWLClass(ontologyPrefix + "#Network_Slice");
		NodeSet<OWLNamedIndividual> networksliceClassIndividuals = r.getInstances(networksliceClass, false);
		System.out.println(networksliceClassIndividuals.entities().count());

		OWLClass cloudsliceClass = dataFactory.getOWLClass(ontologyPrefix + "#Cloud_Slice");
		NodeSet<OWLNamedIndividual> cloudsliceClassIndividuals = r.getInstances(cloudsliceClass, false);
		System.out.println(cloudsliceClassIndividuals.entities().count());

		Iterator<Node<OWLNamedIndividual>> userappIndIter = userappClassIndividuals.iterator();
		while (userappIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = userappIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}
		Iterator<Node<OWLNamedIndividual>> appIndIter = appClassIndividuals.iterator();
		while (appIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = appIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}
		Iterator<Node<OWLNamedIndividual>> appinstanceIndIter = appinstanceClassIndividuals.iterator();
		while (appinstanceIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = appinstanceIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}
		Iterator<Node<OWLNamedIndividual>> networkflavourIndIter = networkflavourClassIndividuals.iterator();
		while (networkflavourIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = networkflavourIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}

		Iterator<Node<OWLNamedIndividual>> cloudflavourIndIter = cloudflavourClassIndividuals.iterator();
		while (cloudflavourIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = cloudflavourIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}

		Iterator<Node<OWLNamedIndividual>> networksliceIndIter = networksliceClassIndividuals.iterator();
		while (networksliceIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = networksliceIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}

		Iterator<Node<OWLNamedIndividual>> cloudsliceIndIter = cloudsliceClassIndividuals.iterator();
		while (cloudsliceIndIter.hasNext()) {
			OWLNamedIndividual sensorInstance = cloudsliceIndIter.next().getRepresentativeElement();
			System.out.println(sensorInstance);
			for (int i = 0; i < sensorObjectProperties.length; i++) {
				OWLObjectProperty objProperty = dataFactory
						.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
				NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
				Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
				while (sensorResultIter.hasNext()) {
					OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
					resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
							+ resultInd.toString() + System.lineSeparator());
				}
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ArrayList<NodeSet> tmparray = new ArrayList<NodeSet>();
		OWLClass UserApp_Availability_Class = dataFactory.getOWLClass(ontologyPrefix + "#UserApp_Availability");
		NodeSet<OWLNamedIndividual> tmp1 = r.getInstances(UserApp_Availability_Class, false);
		System.out.println(tmp1.entities().count());
		tmparray.add(tmp1);
		OWLClass UserApp_Cost_Class = dataFactory.getOWLClass(ontologyPrefix + "#UserApp_Cost");
		NodeSet<OWLNamedIndividual> tmp2 = r.getInstances(UserApp_Cost_Class, false);
		System.out.println(tmp2.entities().count());
		tmparray.add(tmp2);
		OWLClass UserApp_Portability_Class = dataFactory.getOWLClass(ontologyPrefix + "#UserApp_Portability");
		NodeSet<OWLNamedIndividual> tmp3 = r.getInstances(UserApp_Portability_Class, false);
		System.out.println(tmp3.entities().count());
		tmparray.add(tmp3);
		OWLClass UserApp_Reliability_Class = dataFactory.getOWLClass(ontologyPrefix + "#UserApp_Reliability");
		NodeSet<OWLNamedIndividual> tmp4 = r.getInstances(UserApp_Reliability_Class, false);
		System.out.println(tmp4.entities().count());
		tmparray.add(tmp4);
		OWLClass UserApp_Security_Class = dataFactory.getOWLClass(ontologyPrefix + "#UserApp_Security");
		NodeSet<OWLNamedIndividual> tmp5 = r.getInstances(UserApp_Security_Class, false);
		System.out.println(tmp5.entities().count());
		tmparray.add(tmp5);
		OWLClass UserApp_Error_rate_Requirement_Class = dataFactory
				.getOWLClass(ontologyPrefix + "#UserApp_Error_rate_Requirement");
		NodeSet<OWLNamedIndividual> tmp6 = r.getInstances(UserApp_Error_rate_Requirement_Class, false);
		System.out.println(tmp6.entities().count());
		tmparray.add(tmp6);
		OWLClass UserApp_Jitter_Requirement_Class = dataFactory
				.getOWLClass(ontologyPrefix + "#UserApp_Jitter_Requirement");
		NodeSet<OWLNamedIndividual> tmp7 = r.getInstances(UserApp_Jitter_Requirement_Class, false);
		System.out.println(tmp7.entities().count());
		tmparray.add(tmp7);
		OWLClass UserApp_Latency_Requirement_Class = dataFactory
				.getOWLClass(ontologyPrefix + "#UserApp_Latency_Requirement");
		NodeSet<OWLNamedIndividual> tmp8 = r.getInstances(UserApp_Latency_Requirement_Class, false);
		System.out.println(tmp8.entities().count());
		tmparray.add(tmp8);
		OWLClass UserApp_Throughput_Requirement_Class = dataFactory
				.getOWLClass(ontologyPrefix + "#UserApp_Throughput_Requirement");
		NodeSet<OWLNamedIndividual> tmp9 = r.getInstances(UserApp_Throughput_Requirement_Class, false);
		System.out.println(tmp9.entities().count());
		tmparray.add(tmp9);
		String[] Stringarray = { "Business", "Flavour_Data_security", "Flavour_Data_Volume_per_device",
				"Flavour_Density", "Flavour_Energy_efficiency", "Flavour_Isolation", "Flavour_Latency",
				"Flavour_Reliability", "Flavour_Throughput", "Flavour_Traffic_type", "Flavour_Typical_data_profile",
				"Device_Mobility", "Device_Type", "Operation", "Vertical", "Flavour_CPU", "Flavour_RAM",
				"Flavour_Storage", "CPU_Slice", "RAM_Slice", "Storage_Slice", "Availability", "Delay_tolerance",
				"DC_Availability", "Maximum_downlink_throughput_per_UE", "Guaranteed_downlink_throughput_quota",
				"Maximum_downlink_throughput", "Network_slice_energy_efficiency_KPI", "Group_communication_support",
				"Mission_critical_support", "Mission-critical_capability_support", "Mission-critical_service_support",
				"MMTel_support", "Multimedia_Priority_Service", "NB-IoT_Support", "PS_Availability",
				"PS_Prediction_frequency", "Session_and_Service_Continuity_support", "3GPP_5QI",
				"Support_for_non-IP_traffic", "Supported_device_velocity", "Synchronicity_Availability",
				"Maximum_uplink_throughput_per_UE", "Guaranteed_uplink_throughput_quota", "Maximum_uplink_throughput",
				"User_management_openness", "V2X_communication_mode" };
		for (String i : Stringarray) {
			OWLClass String_Class = dataFactory.getOWLClass(ontologyPrefix + "#" + i);
			NodeSet<OWLNamedIndividual> tmp = r.getInstances(String_Class, false);
			System.out.println(tmp.entities().count());
			tmparray.add(tmp);
		}
		for (NodeSet<OWLNamedIndividual> tmp : tmparray) {
			Iterator<Node<OWLNamedIndividual>> x = tmp.iterator();
			while (x.hasNext()) {
				OWLNamedIndividual sensorInstance = x.next().getRepresentativeElement();
				System.out.println(sensorInstance);
				for (int i = 0; i < sensorObjectProperties.length; i++) {
					OWLObjectProperty objProperty = dataFactory
							.getOWLObjectProperty(ontologyPrefix + "#" + sensorObjectProperties[i]);
					NodeSet<OWLNamedIndividual> result = r.getObjectPropertyValues(sensorInstance, objProperty);
					Iterator<Node<OWLNamedIndividual>> sensorResultIter = result.iterator();
					while (sensorResultIter.hasNext()) {
						OWLNamedIndividual resultInd = sensorResultIter.next().getRepresentativeElement();
						resultWriter.write(sensorInstance.toString() + "\t" + objProperty.toString() + "\t"
								+ resultInd.toString() + System.lineSeparator());
					}
				}
			}
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		String typeIRI = "<" + rdfPrefix + "type>";

		String[] classNames = { new String("App"), new String("App_Instance"), new String("UserApp"),
				new String("UserApp_Availability"), new String("UserApp_Cost"), new String("UserApp_Portability"),
				new String("UserApp_Reliability"), new String("UserApp_Security"),
				new String("UserApp_Error_rate_Requirement"), new String("UserApp_Jitter_Requirement"),
				new String("UserApp_Latency_Requirement"), new String("UserApp_Throughput_Requirement"),
				new String("Network_Flavour"), new String("Business"), new String("Flavour_Data_security"),
				new String("Flavour_Data_Volume_per_device"), new String("Flavour_Density"),
				new String("Flavour_Density"), new String("Flavour_Energy_efficiency"), new String("Flavour_Isolation"),
				new String("Flavour_Latency"), new String("Flavour_Reliability"), new String("Flavour_Throughput"),
				new String("Flavour_Traffic_type"), new String("Flavour_Typical_data_profile"),
				new String("Device_Mobility"), new String("Device_Type"), new String("Operation"),
				new String("Vertical"), new String("Cloud_Flavour"), new String("Flavour_CPU"),
				new String("Flavour_RAM"), new String("Flavour_Storage"), new String("Cloud_Slice"),
				new String("CPU_Slice"), new String("RAM_Slice"), new String("Storage_Slice"),
				new String("Network_Slice"), new String("Availability"), new String("Delay_tolerance"),
				new String("DC_Availability"), new String("Maximum_downlink_throughput_per_UE"),
				new String("Guaranteed_downlink_throughput_quota"), new String("Maximum_downlink_throughput"),
				new String("Network_slice_energy_efficiency_KPI"), new String("Group_communication_support"),
				new String("Mission_critical_support"), new String("Mission-critical_capability_support"),
				new String("Mission-critical_service_support"), new String("MMTel_support"),
				new String("Multimedia_Priority_Service"), new String("NB-IoT_Support"), new String("PS_Availability"),
				new String("PS_Prediction_frequency"), new String("Session_and_Service_Continuity_support"),
				new String("3GPP_5QI"), new String("Support_for_non-IP_traffic"),
				new String("Supported_device_velocity"), new String("Synchronicity_Availability"),
				new String("Maximum_uplink_throughput_per_UE"), new String("Guaranteed_uplink_throughput_quota"),
				new String("Maximum_uplink_throughput"), new String("User_management_openness"),
				new String("V2X_communication_mode"), };

		for (int i = 0; i < classNames.length; i++) {
			OWLClass classEntity = dataFactory.getOWLClass(ontologyPrefix + "#" + classNames[i]);
			NodeSet<OWLNamedIndividual> resultsSet = r.getInstances(classEntity, false);
			System.out.println(classNames[i] + " " + resultsSet.entities().count());
			Iterator<Node<OWLNamedIndividual>> iter = resultsSet.iterator();
			while (iter.hasNext()) {
				OWLNamedIndividual ind = iter.next().getRepresentativeElement();
				resultWriter.write(
						ind.toString() + "\t" + typeIRI + "\t" + classEntity.toString() + System.lineSeparator());
			}
		}

		resultWriter.close();
	}

	public static OWLDataFactory dataFactory;
	public static OWLOntology ontology;
	private static final Random random = new Random(1);
	public static final String ontologyPrefix = "http://www.semanticweb.org/hungbui/ontologies/2022/0/ZIMOAPP";
	public static final String rdfPrefix = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String rdfsPrefix = "http://www.w3.org/2000/01/rdf-schema#";
	public static final int APPLICATION_COUNT = 10;
	public static final int ENVIRONMENTAL_SENSOR_COUNT = 4;
	public static final int ROAD_CONDITION_SENSOR_COUNT = 2;
	public static final int WEATHER_SENSOR_COUNT = 2;
	public static final int TRAFFIC_SENSOR_COUNT = 100;
	public static int[] velocityValues = { 0, 10, 20, 30, 40, 60, 90, 120, 150, 200, 300, 450 };

	public static int[] userDensityValues = { 10, 100, 1000 };
	public static int[] CPUValues = { 2, 4, 8, 16, 32 };
	public static int[] RAMValues = { 8, 16, 32, 64, 128 };
	public static int[] StorageValues = { 256, 512, 1024, 2048, 4096 };

	public static double[] Network_Slice_Availability = { 99.0, 99.99, 99.999 };
	public static int[] GPP_5QI = { 3, 15 };
	public static int[] bandwidthValues = { 1, 2, 3, 4 };
	public static int[] UE_Density = { 100, 1000, 10000 };
	public static int[] Max_number_of_PDU_sessions = { 10, 100, 1000 };
	public static int[] velocitySliceValues = { 60, 200, 450 };

}
