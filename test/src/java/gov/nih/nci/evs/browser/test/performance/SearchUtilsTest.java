package gov.nih.nci.evs.browser.test.performance;

import gov.nih.nci.evs.browser.test.utils.*;
import gov.nih.nci.evs.browser.utils.*;
import gov.nih.nci.evs.browser.utils.test.*;

public class SearchUtilsTest extends SearchUtils {
    private int _runAmount = 1;
    private boolean _suppressOtherMessages = true;
    private boolean _displayParameters = false;
    private boolean _displayConcepts = false;
    private boolean _displayResults = true;

    public SearchUtilsTest() {
        super(null);
    }

/*
    public Vector<org.LexGrid.concepts.Concept> searchByName(String scheme,
        String version, String matchText, String source, String matchAlgorithm,
        SortOption sortOption, int maxToReturn) {
        if (_displayParameters) {
            DBG.debug("* Search parameters:");
            DBG.debug("  * scheme = " + scheme);
            DBG.debug("  * version = " + version);
            DBG.debug("  * matchText = " + matchText);
            DBG.debug("  * source = " + source);
            DBG.debug("  * matchAlgorithm = " + matchAlgorithm);
            DBG.debug("  * sortOption = " + sortOption);
            DBG.debug("  * maxToReturn = " + maxToReturn);
        }
        DBG.debug(DBG.isDisplayDetails(), "* Details: " + matchAlgorithm + " " + matchText);
        return super.searchByName(scheme, version, matchText, source,
            matchAlgorithm, sortOption, maxToReturn);
    }


    public void searchByNameTest(String scheme, String version, String matchText,
        String source, String matchAlgorithm, SortOption sortOption,
        int maxToReturn) {
        DBG.clearTabbbedValues();
        Utils.StopWatch stopWatch = new Utils.StopWatch();

        Vector<Concept> vector = searchByName(scheme, version, matchText,
            source, matchAlgorithm, sortOption, maxToReturn);

        long duration = stopWatch.getDuration();

        if (_displayConcepts && vector.size() > 0) {
            DBG.debug("* List of concepts:");
            for (int i = 0; i < vector.size(); i++) {
                int j = i + 1;
                Concept ce = vector.elementAt(i);
                DBG.debug("  " + j + ") " + ce.getEntityCode() + " "
                    + ce.getEntityDescription().getContent());
            }
        }
        if (_displayResults) {
            DBG.debug("* Result: " + matchAlgorithm + " " + matchText);
            DBG.debug("  * Number of concepts: " + vector.size());
            DBG.debug("  * Total run time: " + stopWatch.getResult(duration));
        }
        if (DBG.isDisplayTabDelimitedFormat()) {
            int i=0;
            DBG.debugTabbedValue(i++, "* Tabbed", "");
            DBG.debugTabbedValue(i++, "Keyword", matchText);
            DBG.debugTabbedValue(i++, "Algorithm", matchAlgorithm);
            DBG.debugTabbedValue(i++, "Hits", vector.size());
            DBG.debugTabbedValue(i++, "Run Time", stopWatch.formatInSec(duration));
            DBG.displayTabbedValues();
        }
    }
*/

    private void prompt() {
        boolean isTrue = false;

        DBG.debug("* Prompt (" + getClass().getSimpleName() + "):");
        _runAmount = Prompt.prompt(
            "  * How many concepts", _runAmount);
        _suppressOtherMessages = Prompt.prompt(
            "  * Suppress other debugging messages", _suppressOtherMessages);
        Debug.setDisplay(!_suppressOtherMessages);
        _displayParameters = Prompt.prompt("  * Display parameters",
            _displayParameters);
        isTrue = Prompt.prompt("  * Display details", DBG.isDisplayDetails());
        DBG.setDisplayDetails(isTrue);
        _displayConcepts = Prompt.prompt("  * Display concepts", _displayConcepts);
        _displayResults = Prompt.prompt("  * Display results", _displayResults);
        isTrue = Prompt.prompt("  * Display tab delimited",
            DBG.isDisplayTabDelimitedFormat());
        DBG.setDisplayTabDelimitedFormat(isTrue);
    }

    private void runTest() {
        String scheme = "NCI MetaThesaurus";
        String version = null;
        String matchAlgorithm = "contains";
        String source = null;
        //SortOption sortOption = new SortOption(SortOption.Type.ALL);
        int maxToReturn = -1;
        String[] matchTexts = new String[] {
            "100",
            "bone",
            "blood",
            "allele",
            "tumor",
            "cancer",
            "gene",
            "neoplasm",
            "grade",
            "cell",
            "ctcae",
            "carcinoma",
            "infection",
            "event",
//            "adverse",
            "stage",
            "device",
            "protein",
            "gland",
            "injury"
        };

//        matchAlgorithm = "exactMatch";
//        matchTexts = new String[] { "cell" };

        DBG.debug("* matchTexts: " + Utils.toString(matchTexts));
        DBG.debug("* matchAlgorithm: " + matchAlgorithm);
        prompt();

        for (int i = 0; i < matchTexts.length; ++i) {
            if (i >= _runAmount)
                break;
            String matchText = matchTexts[i];
            if (DBG.isDisplayDetails()) {
                DBG.debug("");
                DBG.debug(Utils.SEPARATOR);
            }
            /*
            searchByNameTest(scheme, version, matchText, source, matchAlgorithm,
                sortOption, maxToReturn);
            */
        }
        DBG.debug("* Done");
    }

    public static void main(String[] args) {
        DBG.setPerformanceTesting(true);
        SearchUtilsTest test = new SearchUtilsTest();
        boolean isContinue = true;
        do {
            test.runTest();
            DBG.debug("");
            DBG.debug(Utils.SEPARATOR);
            isContinue = Prompt.prompt("Rerun", isContinue);
            if (!isContinue)
                break;
        } while (isContinue);
        DBG.debug("Done");
        DBG.setPerformanceTesting(false);
    }
}
