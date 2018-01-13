package deadliner;

/**Poikkeusluokka
 * @author Max
 * @version 28.3.2017
 *
 */
public class Poikkeus extends Exception {
    private static final long serialVersionUID = 1L;

    /**Poikkeuksen muodostaja
     * @param viesti Poikkeusken viesti
     */
    public Poikkeus(String viesti){
        super(viesti);
    }
}
