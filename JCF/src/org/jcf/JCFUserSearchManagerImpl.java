package org.jcf;

import java.util.Collection;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.springframework.util.Assert;

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
public class JCFUserSearchManagerImpl implements JCFUserSearchManager {

	/**
	 * used for delegation
	 */
	private UserSearchManager userSearchManager;
	
	/**
	 * default ctor
	 * @param userSearchManager
	 */
	JCFUserSearchManagerImpl(UserSearchManager userSearchManager) {
		Assert.notNull(userSearchManager);
		this.userSearchManager = userSearchManager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFUserSearchManager#getSearchForm(java.lang.String)
	 */
	public JCFForm getSearchForm(String searchService) {
		Assert.hasLength(searchService);
		try {
			return new JCFFormImpl(userSearchManager.getSearchForm(searchService));
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFUserSearchManager.getSearchForm ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFUserSearchManager#getSearchResults(org.jivesoftware.smackx.Form, java.lang.String)
	 */
	public JCFReportedDataFromSearch getSearchResults(JCFForm searchForm,
			String searchService) {
		Assert.notNull(searchForm);
		Assert.hasLength(searchService);
		try {
			return new JCFReportedDataFromSearchImpl(userSearchManager.getSearchResults(((JCFFormImpl)searchForm).getForm(), searchService));
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFUserSearchManager.getSearchResults ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFUserSearchManager#getSearchServices()
	 */
	@SuppressWarnings("unchecked")
	public Collection<String> getSearchServices() {
		try {
			return userSearchManager.getSearchServices();
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFUserSearchManager.getSearchServices ", e);
		}
	}

}
