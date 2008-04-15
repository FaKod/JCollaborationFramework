package org.jcf;

import java.util.Collection;

/**
* The UserSearchManager is a facade built upon Jabber Search Services (JEP-055) to allow for searching
* repositories on a Jabber Server. This implementation allows for transparency of implementation of
* searching (DataForms or No DataForms), but allows the user to simply use the DataForm model for both
* types of support.
* <pre>
* XMPPConnection con = new XMPPConnection("jabber.org");
* con.login("john", "doe");
* UserSearchManager search = new UserSearchManager(con, "users.jabber.org");
* Form searchForm = search.getSearchForm();
* Form answerForm = searchForm.createAnswerForm();
* answerForm.setAnswer("last", "DeMoro");
* ReportedData data = search.getSearchResults(answerForm);
* // Use Returned Data
* </pre>
*
* @author Derek DeMoro
* @author FaKod
*/
public interface JCFUserSearchManager {
	
	/**
     * Returns the form to fill out to perform a search.
     *
     * @param searchService the search service to query.
     * @return the form to fill out to perform a search.
     * @throws XMPPException thrown if a server error has occurred.
     */
    public JCFForm getSearchForm(String searchService);
    
    /**
     * Submits a search form to the server and returns the resulting information
     * in the form of <code>ReportedData</code>
     *
     * @param searchForm    the <code>Form</code> to submit for searching.
     * @param searchService the name of the search service to use.
     * @return the ReportedData returned by the server.
     * @throws XMPPException thrown if a server error has occurred.
     */
    public JCFReportedDataFromSearch getSearchResults(JCFForm searchForm, String searchService);
    
    /**
     * Returns a collection of search services found on the server.
     *
     * @return a Collection of search services found on the server.
     * @throws XMPPException thrown if a server error has occurred.
     */
    public Collection<String> getSearchServices();
}
