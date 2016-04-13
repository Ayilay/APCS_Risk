import java.util.Map;

/*
 * Instantiates and keeps track of all territories on the map. May get a little ugly because it creates all territories on the map
 * 
 */
public class TerritoryMap
{
	// Holy wall of text, batman

	// Every single territory on the map.

	// North america - 12
	private Territory alaska;
	private Territory alberta;
	private Territory mexico;
	private Territory eastUS;
	private Territory cuba;
	private Territory centralAmerica;
	private Territory northWestTerritory;
	private Territory hawaii;
	private Territory greenland;
	private Territory quebec;
	private Territory ontario;
	private Territory westUS;

	// South america - 5

	private Territory brazil;
	private Territory argentina;
	private Territory peru;
	private Territory venezuela;
	private Territory bolivia;

	// Europe - 13

	private Territory unitedKingdom;
	private Territory iceland;
	private Territory scandinavia;
	private Territory sweden;
	private Territory eastEurope;
	private Territory lowCountries;
	private Territory spain;
	private Territory france;
	private Territory denmark;
	private Territory germany;
	private Territory poland;
	private Territory czechoslovakia;
	private Territory southernEurope;

	// asia - 16

	private Territory saudiArabia;
	private Territory turkey;
	private Territory iran;
	private Territory afganistan;
	private Territory pakistan;
	private Territory india;
	private Territory indochina;
	private Territory china;
	private Territory mongolia;
	private Territory kazakhstan;
	private Territory japan;
	private Territory ural;
	private Territory kamchatka;
	private Territory irkutsk;
	private Territory siberia;
	private Territory yakutsk;

	// Africa - 8

	private Territory morocco;
	private Territory algeria;
	private Territory egypt;
	private Territory eastAfrica;
	private Territory westAfrica;
	private Territory madagascar;
	private Territory southAfrica;
	private Territory congo;

	// Australia - 5

	private Territory eastAustralia;
	private Territory westAustralia;
	private Territory newGuinea;
	private Territory indonesia;
	private Territory philipines;

	// continent collections.
	private Map<String, Territory> northAmerica;
	private Map<String, Territory> southAmerica;
	private Map<String, Territory> africa;
	private Map<String, Territory> asia;
	private Map<String, Territory> europe;
	private Map<String, Territory> australia;

	public TerritoryMap()
	{
		// Here we go...
		// North America
		alaska = new Territory("Alaska");
		alberta = new Territory("Alberta");
		mexico = new Territory("Mexico");
		eastUS = new Territory("Eastern United States");
		cuba = new Territory("Cuba");
		centralAmerica = new Territory("CentralAmerica");
		northWestTerritory = new Territory("Northwest Territory");
		hawaii = new Territory("Hawaii");
		greenland = new Territory("Greenland");
		quebec = new Territory("Quebec");
		ontario = new Territory("Ontario");
		westUS = new Territory("Western United States");

		// South America
		brazil = new Territory("Brazil");
		argentina = new Territory("Argentina");
		peru = new Territory("Peru");
		venezuela = new Territory("Venezuela");
		bolivia = new Territory("Bolivia");

		// Europe
		unitedKingdom = new Territory("United Kingdom");
		iceland = new Territory("Iceland");
		scandinavia = new Territory("Scandinavia");
		sweden = new Territory("Sweden");
		eastEurope = new Territory("Eastern Europe");
		lowCountries = new Territory("Low Countries");
		spain = new Territory("Spain");
		france = new Territory("France");
		denmark = new Territory("Denmark");
		germany = new Territory("Germany");
		poland = new Territory("Poland");
		czechoslovakia = new Territory("Czechoslovakia");
		southernEurope = new Territory("Southern Europe");

		// Africa
		morocco = new Territory("Morocco");
		algeria = new Territory("Algeria");
		egypt = new Territory("Egypt");
		eastAfrica = new Territory("East Africa");
		westAfrica = new Territory("West Africa");
		madagascar = new Territory("Madagascar");
		southAfrica = new Territory("South Africa");
		congo = new Territory("Congo");

		// Asia
		saudiArabia = new Territory("Saudi Arabia");
		turkey = new Territory("Turkey");
		iran = new Territory("Iran");
		afganistan = new Territory("Afganistan");
		pakistan = new Territory("Pakistan");
		india = new Territory("India");
		indochina = new Territory("Indochina");
		china = new Territory("China");
		mongolia = new Territory("Mongolia");
		kazakhstan = new Territory("Kazakhstan");
		japan = new Territory("Japan");
		ural = new Territory("Ural");
		kamchatka = new Territory("Kamchatka");
		irkutsk = new Territory("Irkutsk");
		siberia = new Territory("Siberia");
		yakutsk = new Territory("Yakutsk");

		// Australia

		eastAustralia = new Territory("Eastern Australia");
		westAustralia = new Territory("Western Australia");
		newGuinea = new Territory("New Guinea");
		indonesia = new Territory("Indonesia");
		philipines = new Territory("Philipines");

		// Set each territory's neighbors. This may take a while.
		// North America
		alaska.addNeighbor(northWestTerritory);
		alaska.addNeighbor(alberta);
		alaska.addNeighbor(kamchatka);

		alberta.addNeighbor(alaska);
		alberta.addNeighbor(westUS);
		alberta.addNeighbor(ontario);
		alberta.addNeighbor(northWestTerritory);

		northWestTerritory.addNeighbor(greenland);
		northWestTerritory.addNeighbor(ontario);
		northWestTerritory.addNeighbor(alaska);
		northWestTerritory.addNeighbor(alberta);

		ontario.addNeighbor(northWestTerritory);
		ontario.addNeighbor(greenland);
		ontario.addNeighbor(quebec);
		ontario.addNeighbor(eastUS);
		ontario.addNeighbor(westUS);
		ontario.addNeighbor(alberta);

		greenland.addNeighbor(iceland);
		greenland.addNeighbor(ontario);
		greenland.addNeighbor(quebec);
		greenland.addNeighbor(northWestTerritory);

		quebec.addNeighbor(greenland);
		quebec.addNeighbor(eastUS);
		quebec.addNeighbor(ontario);

		westUS.addNeighbor(eastUS);
		westUS.addNeighbor(hawaii);
		westUS.addNeighbor(alberta);
		westUS.addNeighbor(ontario);
		westUS.addNeighbor(mexico);

		eastUS.addNeighbor(westUS);
		eastUS.addNeighbor(quebec);
		eastUS.addNeighbor(ontario);
		eastUS.addNeighbor(mexico);
		eastUS.addNeighbor(cuba);

		mexico.addNeighbor(westUS);
		mexico.addNeighbor(centralAmerica);
		mexico.addNeighbor(cuba);
		mexico.addNeighbor(eastUS);

		cuba.addNeighbor(eastUS);
		cuba.addNeighbor(mexico);

		centralAmerica.addNeighbor(venezuela);
		centralAmerica.addNeighbor(mexico);

		hawaii.addNeighbor(westUS);
		hawaii.addNeighbor(japan);

		// South America
		venezuela.addNeighbor(peru);
		venezuela.addNeighbor(brazil);
		venezuela.addNeighbor(centralAmerica);

		peru.addNeighbor(venezuela);
		peru.addNeighbor(brazil);
		peru.addNeighbor(bolivia);
		peru.addNeighbor(argentina);

		bolivia.addNeighbor(brazil);
		bolivia.addNeighbor(argentina);
		bolivia.addNeighbor(peru);

		brazil.addNeighbor(peru);
		brazil.addNeighbor(argentina);
		brazil.addNeighbor(bolivia);
		brazil.addNeighbor(venezuela);
		brazil.addNeighbor(westAfrica);

		argentina.addNeighbor(peru);
		argentina.addNeighbor(bolivia);
		argentina.addNeighbor(brazil);

		// Africa
		southAfrica.addNeighbor(madagascar);
		southAfrica.addNeighbor(congo);
		southAfrica.addNeighbor(eastAfrica);

		madagascar.addNeighbor(southAfrica);
		madagascar.addNeighbor(eastAfrica);

		eastAfrica.addNeighbor(congo);
		eastAfrica.addNeighbor(madagascar);
		eastAfrica.addNeighbor(westAfrica);
		eastAfrica.addNeighbor(saudiArabia);
		eastAfrica.addNeighbor(westAfrica);
		eastAfrica.addNeighbor(egypt);

		congo.addNeighbor(eastAfrica);
		congo.addNeighbor(westAfrica);
		congo.addNeighbor(southAfrica);

		westAfrica.addNeighbor(brazil);
		westAfrica.addNeighbor(algeria);
		westAfrica.addNeighbor(morocco);
		westAfrica.addNeighbor(eastAfrica);
		westAfrica.addNeighbor(egypt);
		westAfrica.addNeighbor(congo);

		morocco.addNeighbor(westAfrica);
		morocco.addNeighbor(algeria);
		morocco.addNeighbor(spain);

		algeria.addNeighbor(morocco);
		algeria.addNeighbor(eastAfrica);
		algeria.addNeighbor(egypt);
		algeria.addNeighbor(southernEurope);

		egypt.addNeighbor(algeria);
		egypt.addNeighbor(westAfrica);
		egypt.addNeighbor(westAfrica);
		egypt.addNeighbor(turkey);
		egypt.addNeighbor(southernEurope);

		// Europe
		iceland.addNeighbor(greenland);
		iceland.addNeighbor(unitedKingdom);
		iceland.addNeighbor(scandinavia);

		unitedKingdom.addNeighbor(iceland);
		unitedKingdom.addNeighbor(france);
		unitedKingdom.addNeighbor(scandinavia);
		unitedKingdom.addNeighbor(lowCountries);

		scandinavia.addNeighbor(denmark);
		scandinavia.addNeighbor(iceland);
		scandinavia.addNeighbor(unitedKingdom);
		scandinavia.addNeighbor(poland);
		scandinavia.addNeighbor(germany);
		scandinavia.addNeighbor(lowCountries);
		scandinavia.addNeighbor(sweden);
		scandinavia.addNeighbor(eastEurope);

		denmark.addNeighbor(scandinavia);
		denmark.addNeighbor(germany);

		germany.addNeighbor(poland);
		germany.addNeighbor(czechoslovakia);
		germany.addNeighbor(france);
		germany.addNeighbor(lowCountries);
		germany.addNeighbor(southernEurope);
		germany.addNeighbor(scandinavia);
		germany.addNeighbor(denmark);

		poland.addNeighbor(germany);
		poland.addNeighbor(czechoslovakia);
		poland.addNeighbor(eastEurope);
		poland.addNeighbor(scandinavia);

		france.addNeighbor(unitedKingdom);
		france.addNeighbor(lowCountries);
		france.addNeighbor(germany);
		france.addNeighbor(spain);
		france.addNeighbor(southernEurope);

		spain.addNeighbor(france);
		spain.addNeighbor(morocco);

		lowCountries.addNeighbor(france);
		lowCountries.addNeighbor(germany);
		lowCountries.addNeighbor(unitedKingdom);
		lowCountries.addNeighbor(scandinavia);

		southernEurope.addNeighbor(germany);
		southernEurope.addNeighbor(czechoslovakia);
		southernEurope.addNeighbor(eastEurope);
		southernEurope.addNeighbor(egypt);
		southernEurope.addNeighbor(algeria);
		southernEurope.addNeighbor(france);
		southernEurope.addNeighbor(turkey);

		czechoslovakia.addNeighbor(southernEurope);
		czechoslovakia.addNeighbor(eastEurope);
		czechoslovakia.addNeighbor(poland);
		czechoslovakia.addNeighbor(germany);

		eastEurope.addNeighbor(ural);
		eastEurope.addNeighbor(kazakhstan);
		eastEurope.addNeighbor(iran);
		eastEurope.addNeighbor(turkey);
		eastEurope.addNeighbor(scandinavia);
		eastEurope.addNeighbor(poland);
		eastEurope.addNeighbor(sweden);
		eastEurope.addNeighbor(czechoslovakia);
		eastEurope.addNeighbor(southernEurope);

		// Asia D:
		turkey.addNeighbor(eastEurope);
		turkey.addNeighbor(southernEurope);
		turkey.addNeighbor(egypt);
		turkey.addNeighbor(saudiArabia);
		turkey.addNeighbor(iran);

		saudiArabia.addNeighbor(eastAfrica);
		saudiArabia.addNeighbor(turkey);

		iran.addNeighbor(turkey);
		iran.addNeighbor(eastEurope);
		iran.addNeighbor(pakistan);
		iran.addNeighbor(kazakhstan);
		iran.addNeighbor(afganistan);

		pakistan.addNeighbor(india);
		pakistan.addNeighbor(afganistan);
		pakistan.addNeighbor(china);
		pakistan.addNeighbor(kazakhstan);
		pakistan.addNeighbor(iran);

		afganistan.addNeighbor(iran);
		afganistan.addNeighbor(pakistan);
		afganistan.addNeighbor(kazakhstan);

		kazakhstan.addNeighbor(china);
		kazakhstan.addNeighbor(eastEurope);
		kazakhstan.addNeighbor(iran);
		kazakhstan.addNeighbor(afganistan);
		kazakhstan.addNeighbor(ural);

		ural.addNeighbor(kazakhstan);
		ural.addNeighbor(eastEurope);
		ural.addNeighbor(china);
		ural.addNeighbor(siberia);

		siberia.addNeighbor(ural);
		siberia.addNeighbor(china);
		siberia.addNeighbor(yakutsk);
		siberia.addNeighbor(mongolia);
		siberia.addNeighbor(irkutsk);

		china.addNeighbor(siberia);
		china.addNeighbor(ural);
		china.addNeighbor(mongolia);
		china.addNeighbor(indochina);
		china.addNeighbor(india);
		china.addNeighbor(pakistan);
		china.addNeighbor(kazakhstan);

		india.addNeighbor(china);
		india.addNeighbor(pakistan);
		india.addNeighbor(indochina);

		yakutsk.addNeighbor(kamchatka);
		yakutsk.addNeighbor(irkutsk);
		yakutsk.addNeighbor(siberia);

		irkutsk.addNeighbor(yakutsk);
		irkutsk.addNeighbor(kamchatka);
		irkutsk.addNeighbor(siberia);
		irkutsk.addNeighbor(mongolia);

		mongolia.addNeighbor(japan);
		mongolia.addNeighbor(china);
		mongolia.addNeighbor(irkutsk);
		mongolia.addNeighbor(siberia);
		mongolia.addNeighbor(kamchatka);

		kamchatka.addNeighbor(mongolia);
		kamchatka.addNeighbor(alaska);
		kamchatka.addNeighbor(irkutsk);
		kamchatka.addNeighbor(yakutsk);
		kamchatka.addNeighbor(japan);

		indochina.addNeighbor(china);
		indochina.addNeighbor(india);
		indochina.addNeighbor(indonesia);
		indochina.addNeighbor(philipines);

		japan.addNeighbor(hawaii);
		japan.addNeighbor(kamchatka);
		japan.addNeighbor(mongolia);

		// Australia
		eastAustralia.addNeighbor(westAustralia);
		eastAustralia.addNeighbor(newGuinea);
		eastAustralia.addNeighbor(indonesia);

		westAustralia.addNeighbor(eastAustralia);
		westAustralia.addNeighbor(newGuinea);

		newGuinea.addNeighbor(westAustralia);
		newGuinea.addNeighbor(eastAustralia);
		newGuinea.addNeighbor(indonesia);

		indonesia.addNeighbor(indochina);
		indonesia.addNeighbor(eastAustralia);
		indonesia.addNeighbor(philipines);

		philipines.addNeighbor(indochina);
		philipines.addNeighbor(indonesia);

		// Add territories to continent maps
		// North America
		northAmerica.put(alaska.getID(), alaska);
		northAmerica.put(northWestTerritory.getID(), northWestTerritory);
		northAmerica.put(greenland.getID(), greenland);
		northAmerica.put(alberta.getID(), alberta);
		northAmerica.put(ontario.getID(), ontario);
		northAmerica.put(quebec.getID(), quebec);
		northAmerica.put(hawaii.getID(), hawaii);
		northAmerica.put(westUS.getID(), westUS);
		northAmerica.put(eastUS.getID(), eastUS);
		northAmerica.put(mexico.getID(), mexico);
		northAmerica.put(cuba.getID(), cuba);
		northAmerica.put(centralAmerica.getID(), centralAmerica);
		
		//South America
		southAmerica.put(venezuela.getID(), venezuela);
		southAmerica.put(argentina.getID(), argentina);
		southAmerica.put(peru.getID(), peru);
		southAmerica.put(bolivia.getID(), bolivia);
		southAmerica.put(brazil.getID(), brazil);
		
		//Africa
		africa.put(southAfrica.getID(), southAfrica);
		africa.put(madagascar.getID(), madagascar);
		africa.put(congo.getID(), congo);
		africa.put(eastAfrica.getID(), eastAfrica);
		africa.put(westAfrica.getID(), westAfrica);
		africa.put(egypt.getID(), egypt);
		africa.put(algeria.getID(), algeria);
		africa.put(morocco.getID(), morocco);
		
		//Europe
		europe.put(spain.getID(), spain);
		europe.put(france.getID(), france);
		europe.put(lowCountries.getID(), lowCountries);
		europe.put(germany.getID(),germany);
		europe.put(southernEurope.getID(),southernEurope);
		europe.put(poland.getID(), poland);
		europe.put(eastEurope.getID(),eastEurope);
		europe.put(czechoslovakia.getID(), czechoslovakia);
		europe.put(denmark.getID(),denmark);
		europe.put(scandinavia.getID(),scandinavia);
		europe.put(sweden.getID(),sweden);
		europe.put(iceland.getID(),iceland);
		europe.put(unitedKingdom.getID(),unitedKingdom);
		
		//Asia
		asia.put(saudiArabia.getID(), saudiArabia);
		asia.put(turkey.getID(),turkey);
		asia.put(iran.getID(),iran);
		asia.put(pakistan.getID(),pakistan);
		asia.put(afganistan.getID(),afganistan);
		asia.put(kazakhstan.getID(),kazakhstan);
		asia.put(ural.getID(),ural);
		asia.put(siberia.getID(),siberia);
		asia.put(china.getID(),china);
		asia.put(india.getID(),india);
		asia.put(irkutsk.getID(),irkutsk);
		asia.put(yakutsk.getID(),yakutsk);
		asia.put(mongolia.getID(),mongolia);
		asia.put(indochina.getID(),indochina);
		asia.put(kamchatka.getID(),kamchatka);
		asia.put(japan.getID(),japan);
		
		//Australia
		australia.put(philipines.getID(),philipines);
		australia.put(indonesia.getID(),indonesia);
		australia.put(newGuinea.getID(),newGuinea);
		australia.put(eastAustralia.getID(),eastAustralia);
		australia.put(westAustralia.getID(),westAustralia);
	}
}
