package territoryMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import main.Player;

/*
 *
 * A "static" class that instantiates and keeps track of all territories on the map.
 * May get a little ugly because it creates all territories on the map
 *
 */

public class TerritoryMap
{
	// all territories and continents (groups of territories)
	private static Map<String, Territory> allTerritories;
	private static Map<String, Continent> allContinents;

	// Make sure nothing can instantiate a TerritoryMap
	private TerritoryMap() {}

	public static void init()
	{
		// Initialize Continents
		Continent northAmerica = new Continent("North America", 5);
		Continent southAmerica = new Continent("South America", 3);
		Continent africa = new Continent("Africa", 4);
		Continent asia = new Continent("Asia", 8);
		Continent europe = new Continent("Europe", 6);
		Continent australia = new Continent("Australia", 3);

		// Initialize Maps
		allTerritories = new HashMap<String, Territory>();
		allContinents = new HashMap<String, Continent> ();

		// Here we go...
		// North America
		Territory alaska = new Territory("Alaska");
		Territory alberta = new Territory("Alberta");
		Territory mexico = new Territory("Mexico");
		Territory eastUS = new Territory("Eastern United States");
		Territory cuba = new Territory("Cuba");
		Territory centralAmerica = new Territory("Central America");
		Territory northWestTerritory = new Territory("Northwest Territory");
		Territory hawaii = new Territory("Hawaii");
		Territory greenland = new Territory("Greenland");
		Territory quebec = new Territory("Quebec");
		Territory ontario = new Territory("Ontario");
		Territory westUS = new Territory("Western United States");

		// South America
		Territory brazil = new Territory("Brazil");
		Territory argentina = new Territory("Argentina");
		Territory peru = new Territory("Peru");
		Territory venezuela = new Territory("Venezuela");
		Territory bolivia = new Territory("Bolivia");

		// Europe
		Territory unitedKingdom = new Territory("United Kingdom");
		Territory iceland = new Territory("Iceland");
		Territory scandinavia = new Territory("Scandinavia");
		Territory sweden = new Territory("Sweden");
		Territory eastEurope = new Territory("Eastern Europe");
		Territory lowCountries = new Territory("Low Countries");
		Territory spain = new Territory("Spain");
		Territory france = new Territory("France");
		Territory denmark = new Territory("Denmark");
		Territory germany = new Territory("Germany");
		Territory poland = new Territory("Poland");
		Territory czechoslovakia = new Territory("Czechoslovakia");
		Territory southernEurope = new Territory("Southern Europe");

		// Africa
		Territory morocco = new Territory("Morocco");
		Territory algeria = new Territory("Algeria");
		Territory egypt = new Territory("Egypt");
		Territory eastAfrica = new Territory("East Africa");
		Territory westAfrica = new Territory("West Africa");
		Territory madagascar = new Territory("Madagascar");
		Territory southAfrica = new Territory("South Africa");
		Territory congo = new Territory("Congo");

		// Asia
		Territory saudiArabia = new Territory("Saudi Arabia");
		Territory turkey = new Territory("Turkey");
		Territory iran = new Territory("Iran");
		Territory afganistan = new Territory("Afganistan");
		Territory pakistan = new Territory("Pakistan");
		Territory india = new Territory("India");
		Territory indochina = new Territory("Indochina");
		Territory china = new Territory("China");
		Territory mongolia = new Territory("Mongolia");
		Territory kazakhstan = new Territory("Kazakhstan");
		Territory japan = new Territory("Japan");
		Territory ural = new Territory("Ural");
		Territory kamchatka = new Territory("Kamchatka");
		Territory irkutsk = new Territory("Irkutsk");
		Territory siberia = new Territory("Siberia");
		Territory yakutsk = new Territory("Yakutsk");

		// Australia

		Territory eastAustralia = new Territory("Eastern Australia");
		Territory westAustralia = new Territory("Western Australia");
		Territory newGuinea = new Territory("New Guinea");
		Territory indonesia = new Territory("Indonesia");
		Territory philippines = new Territory("Philippines");

		////////////////////////////////////////////////////////////
		// Set each territory's neighbors. This may take a while.
		////////////////////////////////////////////////////////////

		// North America
		alaska.addNeighbor(northWestTerritory.getID());
		alaska.addNeighbor(alberta.getID());
		alaska.addNeighbor(kamchatka.getID());

		alberta.addNeighbor(alaska.getID());
		alberta.addNeighbor(westUS.getID());
		alberta.addNeighbor(ontario.getID());
		alberta.addNeighbor(northWestTerritory.getID());

		northWestTerritory.addNeighbor(greenland.getID());
		northWestTerritory.addNeighbor(ontario.getID());
		northWestTerritory.addNeighbor(alaska.getID());
		northWestTerritory.addNeighbor(alberta.getID());

		ontario.addNeighbor(northWestTerritory.getID());
		ontario.addNeighbor(greenland.getID());
		ontario.addNeighbor(quebec.getID());
		ontario.addNeighbor(eastUS.getID());
		ontario.addNeighbor(westUS.getID());
		ontario.addNeighbor(alberta.getID());

		greenland.addNeighbor(iceland.getID());
		greenland.addNeighbor(ontario.getID());
		greenland.addNeighbor(quebec.getID());
		greenland.addNeighbor(northWestTerritory.getID());

		quebec.addNeighbor(greenland.getID());
		quebec.addNeighbor(eastUS.getID());
		quebec.addNeighbor(ontario.getID());

		westUS.addNeighbor(eastUS.getID());
		westUS.addNeighbor(hawaii.getID());
		westUS.addNeighbor(alberta.getID());
		westUS.addNeighbor(ontario.getID());
		westUS.addNeighbor(mexico.getID());

		eastUS.addNeighbor(westUS.getID());
		eastUS.addNeighbor(quebec.getID());
		eastUS.addNeighbor(ontario.getID());
		eastUS.addNeighbor(mexico.getID());
		eastUS.addNeighbor(cuba.getID());

		mexico.addNeighbor(westUS.getID());
		mexico.addNeighbor(centralAmerica.getID());
		mexico.addNeighbor(cuba.getID());
		mexico.addNeighbor(eastUS.getID());

		cuba.addNeighbor(eastUS.getID());
		cuba.addNeighbor(mexico.getID());

		centralAmerica.addNeighbor(venezuela.getID());
		centralAmerica.addNeighbor(mexico.getID());

		hawaii.addNeighbor(westUS.getID());
		hawaii.addNeighbor(japan.getID());

		// South America
		venezuela.addNeighbor(peru.getID());
		venezuela.addNeighbor(brazil.getID());
		venezuela.addNeighbor(centralAmerica.getID());

		peru.addNeighbor(venezuela.getID());
		peru.addNeighbor(brazil.getID());
		peru.addNeighbor(bolivia.getID());
		peru.addNeighbor(argentina.getID());

		bolivia.addNeighbor(brazil.getID());
		bolivia.addNeighbor(argentina.getID());
		bolivia.addNeighbor(peru.getID());

		brazil.addNeighbor(peru.getID());
		brazil.addNeighbor(argentina.getID());
		brazil.addNeighbor(bolivia.getID());
		brazil.addNeighbor(venezuela.getID());
		brazil.addNeighbor(westAfrica.getID());

		argentina.addNeighbor(peru.getID());
		argentina.addNeighbor(bolivia.getID());
		argentina.addNeighbor(brazil.getID());

		// Africa
		southAfrica.addNeighbor(madagascar.getID());
		southAfrica.addNeighbor(congo.getID());
		southAfrica.addNeighbor(eastAfrica.getID());

		madagascar.addNeighbor(southAfrica.getID());
		madagascar.addNeighbor(eastAfrica.getID());

		eastAfrica.addNeighbor(congo.getID());
		eastAfrica.addNeighbor(madagascar.getID());
		eastAfrica.addNeighbor(westAfrica.getID());
		eastAfrica.addNeighbor(saudiArabia.getID());
		eastAfrica.addNeighbor(westAfrica.getID());
		eastAfrica.addNeighbor(egypt.getID());

		congo.addNeighbor(eastAfrica.getID());
		congo.addNeighbor(westAfrica.getID());
		congo.addNeighbor(southAfrica.getID());

		westAfrica.addNeighbor(brazil.getID());
		westAfrica.addNeighbor(algeria.getID());
		westAfrica.addNeighbor(morocco.getID());
		westAfrica.addNeighbor(eastAfrica.getID());
		westAfrica.addNeighbor(egypt.getID());
		westAfrica.addNeighbor(congo.getID());

		morocco.addNeighbor(westAfrica.getID());
		morocco.addNeighbor(algeria.getID());
		morocco.addNeighbor(spain.getID());

		algeria.addNeighbor(morocco.getID());
		algeria.addNeighbor(eastAfrica.getID());
		algeria.addNeighbor(egypt.getID());
		algeria.addNeighbor(southernEurope.getID());

		egypt.addNeighbor(algeria.getID());
		egypt.addNeighbor(westAfrica.getID());
		egypt.addNeighbor(turkey.getID());
		egypt.addNeighbor(southernEurope.getID());
		egypt.addNeighbor(eastAfrica.getID());
		egypt.addNeighbor(saudiArabia.getID());

		// Europe
		iceland.addNeighbor(greenland.getID());
		iceland.addNeighbor(unitedKingdom.getID());
		iceland.addNeighbor(scandinavia.getID());

		unitedKingdom.addNeighbor(iceland.getID());
		unitedKingdom.addNeighbor(france.getID());
		unitedKingdom.addNeighbor(scandinavia.getID());
		unitedKingdom.addNeighbor(lowCountries.getID());

		scandinavia.addNeighbor(denmark.getID());
		scandinavia.addNeighbor(iceland.getID());
		scandinavia.addNeighbor(unitedKingdom.getID());
		scandinavia.addNeighbor(poland.getID());
		scandinavia.addNeighbor(germany.getID());
		scandinavia.addNeighbor(lowCountries.getID());
		scandinavia.addNeighbor(sweden.getID());
		scandinavia.addNeighbor(eastEurope.getID());

		sweden.addNeighbor(scandinavia.getID());
		sweden.addNeighbor(eastEurope.getID());

		denmark.addNeighbor(scandinavia.getID());
		denmark.addNeighbor(germany.getID());

		germany.addNeighbor(poland.getID());
		germany.addNeighbor(czechoslovakia.getID());
		germany.addNeighbor(france.getID());
		germany.addNeighbor(lowCountries.getID());
		germany.addNeighbor(southernEurope.getID());
		germany.addNeighbor(scandinavia.getID());
		germany.addNeighbor(denmark.getID());

		poland.addNeighbor(germany.getID());
		poland.addNeighbor(czechoslovakia.getID());
		poland.addNeighbor(eastEurope.getID());
		poland.addNeighbor(scandinavia.getID());

		france.addNeighbor(unitedKingdom.getID());
		france.addNeighbor(lowCountries.getID());
		france.addNeighbor(germany.getID());
		france.addNeighbor(spain.getID());
		france.addNeighbor(southernEurope.getID());

		spain.addNeighbor(france.getID());
		spain.addNeighbor(morocco.getID());

		lowCountries.addNeighbor(france.getID());
		lowCountries.addNeighbor(germany.getID());
		lowCountries.addNeighbor(unitedKingdom.getID());
		lowCountries.addNeighbor(scandinavia.getID());

		southernEurope.addNeighbor(germany.getID());
		southernEurope.addNeighbor(czechoslovakia.getID());
		southernEurope.addNeighbor(eastEurope.getID());
		southernEurope.addNeighbor(egypt.getID());
		southernEurope.addNeighbor(algeria.getID());
		southernEurope.addNeighbor(france.getID());
		southernEurope.addNeighbor(turkey.getID());

		czechoslovakia.addNeighbor(southernEurope.getID());
		czechoslovakia.addNeighbor(eastEurope.getID());
		czechoslovakia.addNeighbor(poland.getID());
		czechoslovakia.addNeighbor(germany.getID());

		eastEurope.addNeighbor(ural.getID());
		eastEurope.addNeighbor(kazakhstan.getID());
		eastEurope.addNeighbor(iran.getID());
		eastEurope.addNeighbor(turkey.getID());
		eastEurope.addNeighbor(scandinavia.getID());
		eastEurope.addNeighbor(poland.getID());
		eastEurope.addNeighbor(sweden.getID());
		eastEurope.addNeighbor(czechoslovakia.getID());
		eastEurope.addNeighbor(southernEurope.getID());

		// Asia D:
		turkey.addNeighbor(eastEurope.getID());
		turkey.addNeighbor(southernEurope.getID());
		turkey.addNeighbor(egypt.getID());
		turkey.addNeighbor(saudiArabia.getID());
		turkey.addNeighbor(iran.getID());

		saudiArabia.addNeighbor(eastAfrica.getID());
		saudiArabia.addNeighbor(turkey.getID());
		saudiArabia.addNeighbor(egypt.getID());

		iran.addNeighbor(turkey.getID());
		iran.addNeighbor(eastEurope.getID());
		iran.addNeighbor(pakistan.getID());
		iran.addNeighbor(kazakhstan.getID());
		iran.addNeighbor(afganistan.getID());

		pakistan.addNeighbor(india.getID());
		pakistan.addNeighbor(afganistan.getID());
		pakistan.addNeighbor(china.getID());
		pakistan.addNeighbor(kazakhstan.getID());
		pakistan.addNeighbor(iran.getID());

		afganistan.addNeighbor(iran.getID());
		afganistan.addNeighbor(pakistan.getID());
		afganistan.addNeighbor(kazakhstan.getID());

		kazakhstan.addNeighbor(china.getID());
		kazakhstan.addNeighbor(eastEurope.getID());
		kazakhstan.addNeighbor(iran.getID());
		kazakhstan.addNeighbor(afganistan.getID());
		kazakhstan.addNeighbor(ural.getID());

		ural.addNeighbor(kazakhstan.getID());
		ural.addNeighbor(eastEurope.getID());
		ural.addNeighbor(china.getID());
		ural.addNeighbor(siberia.getID());

		siberia.addNeighbor(ural.getID());
		siberia.addNeighbor(china.getID());
		siberia.addNeighbor(yakutsk.getID());
		siberia.addNeighbor(mongolia.getID());
		siberia.addNeighbor(irkutsk.getID());

		china.addNeighbor(siberia.getID());
		china.addNeighbor(ural.getID());
		china.addNeighbor(mongolia.getID());
		china.addNeighbor(indochina.getID());
		china.addNeighbor(india.getID());
		china.addNeighbor(pakistan.getID());
		china.addNeighbor(kazakhstan.getID());
		china.addNeighbor(philippines.getID());

		india.addNeighbor(china.getID());
		india.addNeighbor(pakistan.getID());
		india.addNeighbor(indochina.getID());

		yakutsk.addNeighbor(kamchatka.getID());
		yakutsk.addNeighbor(irkutsk.getID());
		yakutsk.addNeighbor(siberia.getID());

		irkutsk.addNeighbor(yakutsk.getID());
		irkutsk.addNeighbor(kamchatka.getID());
		irkutsk.addNeighbor(siberia.getID());
		irkutsk.addNeighbor(mongolia.getID());

		mongolia.addNeighbor(japan.getID());
		mongolia.addNeighbor(china.getID());
		mongolia.addNeighbor(irkutsk.getID());
		mongolia.addNeighbor(siberia.getID());
		mongolia.addNeighbor(kamchatka.getID());

		kamchatka.addNeighbor(mongolia.getID());
		kamchatka.addNeighbor(alaska.getID());
		kamchatka.addNeighbor(irkutsk.getID());
		kamchatka.addNeighbor(yakutsk.getID());
		kamchatka.addNeighbor(japan.getID());

		indochina.addNeighbor(china.getID());
		indochina.addNeighbor(india.getID());
		indochina.addNeighbor(indonesia.getID());
		indochina.addNeighbor(philippines.getID());

		japan.addNeighbor(hawaii.getID());
		japan.addNeighbor(kamchatka.getID());
		japan.addNeighbor(mongolia.getID());

		// Australia
		eastAustralia.addNeighbor(westAustralia.getID());
		eastAustralia.addNeighbor(newGuinea.getID());
		eastAustralia.addNeighbor(indonesia.getID());

		westAustralia.addNeighbor(eastAustralia.getID());
		westAustralia.addNeighbor(newGuinea.getID());
		westAustralia.addNeighbor(indonesia.getID());

		newGuinea.addNeighbor(westAustralia.getID());
		newGuinea.addNeighbor(eastAustralia.getID());
		newGuinea.addNeighbor(indonesia.getID());

		indonesia.addNeighbor(indochina.getID());
		indonesia.addNeighbor(eastAustralia.getID());
		indonesia.addNeighbor(philippines.getID());
		indonesia.addNeighbor(newGuinea.getID());
		indonesia.addNeighbor(westAustralia.getID());

		philippines.addNeighbor(indochina.getID());
		philippines.addNeighbor(indonesia.getID());
		philippines.addNeighbor(china.getID());

		////////////////////////////////////////////////////////////
		// Add territories to continent maps
		////////////////////////////////////////////////////////////

		// North America
		northAmerica.addTerritory(alaska.getID());
		northAmerica.addTerritory(northWestTerritory.getID());
		northAmerica.addTerritory(greenland.getID());
		northAmerica.addTerritory(alberta.getID());
		northAmerica.addTerritory(ontario.getID());
		northAmerica.addTerritory(quebec.getID());
		northAmerica.addTerritory(hawaii.getID());
		northAmerica.addTerritory(westUS.getID());
		northAmerica.addTerritory(eastUS.getID());
		northAmerica.addTerritory(mexico.getID());
		northAmerica.addTerritory(cuba.getID());
		northAmerica.addTerritory(centralAmerica.getID());

		// South America
		southAmerica.addTerritory(venezuela.getID());
		southAmerica.addTerritory(argentina.getID());
		southAmerica.addTerritory(peru.getID());
		southAmerica.addTerritory(bolivia.getID());
		southAmerica.addTerritory(brazil.getID());

		// Africa
		africa.addTerritory(southAfrica.getID());
		africa.addTerritory(madagascar.getID());
		africa.addTerritory(congo.getID());
		africa.addTerritory(eastAfrica.getID());
		africa.addTerritory(westAfrica.getID());
		africa.addTerritory(egypt.getID());
		africa.addTerritory(algeria.getID());
		africa.addTerritory(morocco.getID());

		// Europe
		europe.addTerritory(spain.getID());
		europe.addTerritory(france.getID());
		europe.addTerritory(lowCountries.getID());
		europe.addTerritory(germany.getID());
		europe.addTerritory(southernEurope.getID());
		europe.addTerritory(poland.getID());
		europe.addTerritory(eastEurope.getID());
		europe.addTerritory(czechoslovakia.getID());
		europe.addTerritory(denmark.getID());
		europe.addTerritory(scandinavia.getID());
		europe.addTerritory(sweden.getID());
		europe.addTerritory(iceland.getID());
		europe.addTerritory(unitedKingdom.getID());

		// Asia
		asia.addTerritory(saudiArabia.getID());
		asia.addTerritory(turkey.getID());
		asia.addTerritory(iran.getID());
		asia.addTerritory(pakistan.getID());
		asia.addTerritory(afganistan.getID());
		asia.addTerritory(kazakhstan.getID());
		asia.addTerritory(ural.getID());
		asia.addTerritory(siberia.getID());
		asia.addTerritory(china.getID());
		asia.addTerritory(india.getID());
		asia.addTerritory(irkutsk.getID());
		asia.addTerritory(yakutsk.getID());
		asia.addTerritory(mongolia.getID());
		asia.addTerritory(indochina.getID());
		asia.addTerritory(kamchatka.getID());
		asia.addTerritory(japan.getID());

		// Australia
		australia.addTerritory(philippines.getID());
		australia.addTerritory(indonesia.getID());
		australia.addTerritory(newGuinea.getID());
		australia.addTerritory(eastAustralia.getID());
		australia.addTerritory(westAustralia.getID());

		////////////////////////////////////////////////////////////
		// Add all territories to allTerritories
		////////////////////////////////////////////////////////////

		// North America
		allTerritories.put(alaska.getID(), alaska);
		allTerritories.put(alberta.getID(), alberta);
		allTerritories.put(mexico.getID(), mexico);
		allTerritories.put(eastUS.getID(), eastUS);
		allTerritories.put(cuba.getID(), cuba);
		allTerritories.put(centralAmerica.getID(), centralAmerica);
		allTerritories.put(northWestTerritory.getID(), northWestTerritory);
		allTerritories.put(hawaii.getID(), hawaii);
		allTerritories.put(greenland.getID(), greenland);
		allTerritories.put(quebec.getID(), quebec);
		allTerritories.put(ontario.getID(), ontario);
		allTerritories.put(westUS.getID(), westUS);

		// South America
		allTerritories.put(brazil.getID(), brazil);
		allTerritories.put(argentina.getID(), argentina);
		allTerritories.put(peru.getID(), peru);
		allTerritories.put(venezuela.getID(), venezuela);
		allTerritories.put(bolivia.getID(), bolivia);

		// Europe
		allTerritories.put(unitedKingdom.getID(), unitedKingdom);
		allTerritories.put(iceland.getID(), iceland);
		allTerritories.put(scandinavia.getID(), scandinavia);
		allTerritories.put(sweden.getID(), sweden);
		allTerritories.put(eastEurope.getID(), eastEurope);
		allTerritories.put(lowCountries.getID(), lowCountries);
		allTerritories.put(spain.getID(), spain);
		allTerritories.put(france.getID(), france);
		allTerritories.put(denmark.getID(), denmark);
		allTerritories.put(germany.getID(), germany);
		allTerritories.put(poland.getID(), poland);
		allTerritories.put(czechoslovakia.getID(), czechoslovakia);
		allTerritories.put(southernEurope.getID(), southernEurope);

		// Asia
		allTerritories.put(saudiArabia.getID(), saudiArabia);
		allTerritories.put(turkey.getID(), turkey);
		allTerritories.put(iran.getID(), iran);
		allTerritories.put(afganistan.getID(), afganistan);
		allTerritories.put(pakistan.getID(), pakistan);
		allTerritories.put(india.getID(), india);
		allTerritories.put(indochina.getID(), indochina);
		allTerritories.put(china.getID(), china);
		allTerritories.put(mongolia.getID(), mongolia);
		allTerritories.put(kazakhstan.getID(), kazakhstan);
		allTerritories.put(japan.getID(), japan);
		allTerritories.put(ural.getID(), ural);
		allTerritories.put(kamchatka.getID(), kamchatka);
		allTerritories.put(irkutsk.getID(), irkutsk);
		allTerritories.put(siberia.getID(), siberia);
		allTerritories.put(yakutsk.getID(), yakutsk);

		// Africa
		allTerritories.put(morocco.getID(), morocco);
		allTerritories.put(algeria.getID(), algeria);
		allTerritories.put(egypt.getID(), egypt);
		allTerritories.put(eastAfrica.getID(), eastAfrica);
		allTerritories.put(westAfrica.getID(), westAfrica);
		allTerritories.put(madagascar.getID(), madagascar);
		allTerritories.put(southAfrica.getID(), southAfrica);
		allTerritories.put(congo.getID(), congo);

		// Australia
		allTerritories.put(eastAustralia.getID(), eastAustralia);
		allTerritories.put(westAustralia.getID(), westAustralia);
		allTerritories.put(newGuinea.getID(), newGuinea);
		allTerritories.put(indonesia.getID(), indonesia);
		allTerritories.put(philippines.getID(), philippines);

		////////////////////////////////////////////////////////////
		// Put the Continents in allContinents
		////////////////////////////////////////////////////////////

		allContinents.put(northAmerica.getID(), northAmerica);
		allContinents.put(southAmerica.getID(), southAmerica);
		allContinents.put(africa.getID(), africa);
		allContinents.put(asia.getID(), asia);
		allContinents.put(europe.getID(), europe);
		allContinents.put(australia.getID(), australia);
	}

	public static Territory get(String id)
	{
		return allTerritories.get(id);
	}

	public static boolean isValidTerritory(String id)
	{
		return allTerritories.containsKey(id);
	}

	public static boolean continentIsSubsetOfSet(String continent, Set<String> occupied)
	{
		// returns true if every single territory in "continent" is contained in "occupied"
		return occupied.containsAll(allContinents.get(continent).getTerritories());
	}

	public static int calculateArmyBonusFromContinents(Set<String> occupied)
	{
		int totalBonus = 0;

		for(String continent : allContinents.keySet())
		{
			if(continentIsSubsetOfSet(continent, occupied))
				totalBonus += allContinents.get(continent).getArmyBonus();
		}

		return totalBonus;
	}

	public static Set<String> getAllTerritories()
	{
		return allTerritories.keySet();
	}

	public static Set<String> getAllContinents()
	{
		return allContinents.keySet();
	}

	public static int getNumArmiesDeployedOn(String id)
	{
		return allTerritories.get(id).getNumArmies();
	}

	public static Player getOccupierOnTerritory(String id)
	{
		return allTerritories.get(id).getOccupier();
	}

	public static void transferArmies(String territoryToFortifyFromID, String territoryToFortifyID, int numArmies)
	{
		allTerritories.get(territoryToFortifyFromID).moveArmies(territoryToFortifyID, numArmies);
	}
}
