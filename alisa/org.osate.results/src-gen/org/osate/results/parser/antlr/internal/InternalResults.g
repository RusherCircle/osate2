/*
* generated by Xtext
*/
grammar InternalResults;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.osate.results.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.osate.results.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.osate.results.services.ResultsGrammarAccess;

}

@parser::members {

 	private ResultsGrammarAccess grammarAccess;
 	
    public InternalResultsParser(TokenStream input, ResultsGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "ResultReport";	
   	}
   	
   	@Override
   	protected ResultsGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleResultReport
entryRuleResultReport returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getResultReportRule()); }
	 iv_ruleResultReport=ruleResultReport 
	 { $current=$iv_ruleResultReport.current; } 
	 EOF 
;

// Rule ResultReport
ruleResultReport returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='report' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getResultReportAccess().getReportKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getResultReportAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)(	otherlv_2=':' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getResultReportAccess().getColonKeyword_2_0());
    }
(
(
		lv_title_3_0=RULE_STRING
		{
			newLeafNode(lv_title_3_0, grammarAccess.getResultReportAccess().getTitleSTRINGTerminalRuleCall_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"title",
        		lv_title_3_0, 
        		"STRING");
	    }

)
))?	otherlv_4='[' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getResultReportAccess().getLeftSquareBracketKeyword_3());
    }
	otherlv_5='root' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getResultReportAccess().getRootKeyword_4());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getResultReportAccess().getRootEObjectCrossReference_5_0()); 
	    }
		ruleURIID		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_7='description' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getResultReportAccess().getDescriptionKeyword_6_0());
    }
(
(
		lv_decription_8_0=RULE_STRING
		{
			newLeafNode(lv_decription_8_0, grammarAccess.getResultReportAccess().getDecriptionSTRINGTerminalRuleCall_6_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"decription",
        		lv_decription_8_0, 
        		"STRING");
	    }

)
))?(	otherlv_9='heading' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getResultReportAccess().getHeadingKeyword_7_0());
    }
(
(
		lv_heading_10_0=RULE_STRING
		{
			newLeafNode(lv_heading_10_0, grammarAccess.getResultReportAccess().getHeadingSTRINGTerminalRuleCall_7_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"heading",
        		lv_heading_10_0, 
        		"STRING");
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getResultReportAccess().getContentResultReportEntryParserRuleCall_7_2_0()); 
	    }
		lv_content_11_0=ruleResultReportEntry		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultReportRule());
	        }
       		add(
       			$current, 
       			"content",
        		lv_content_11_0, 
        		"ResultReportEntry");
	        afterParserOrEnumRuleCall();
	    }

)
)*)?(	otherlv_12='data' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getResultReportAccess().getDataKeyword_8_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultReportAccess().getResultDataResultDataParserRuleCall_8_1_0()); 
	    }
		lv_resultData_13_0=ruleResultData		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultReportRule());
	        }
       		set(
       			$current, 
       			"resultData",
        		lv_resultData_13_0, 
        		"ResultData");
	        afterParserOrEnumRuleCall();
	    }

)
))?	otherlv_14=']' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getResultReportAccess().getRightSquareBracketKeyword_9());
    }
)
;





// Entry rule entryRuleResultReportEntry
entryRuleResultReportEntry returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getResultReportEntryRule()); }
	 iv_ruleResultReportEntry=ruleResultReportEntry 
	 { $current=$iv_ruleResultReportEntry.current; } 
	 EOF 
;

// Rule ResultReportEntry
ruleResultReportEntry returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='entry' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getResultReportEntryAccess().getEntryKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getResultReportEntryAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportEntryRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)(	otherlv_2=':' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getResultReportEntryAccess().getColonKeyword_2_0());
    }
(
(
		lv_title_3_0=RULE_STRING
		{
			newLeafNode(lv_title_3_0, grammarAccess.getResultReportEntryAccess().getTitleSTRINGTerminalRuleCall_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportEntryRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"title",
        		lv_title_3_0, 
        		"STRING");
	    }

)
))?	otherlv_4='[' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getResultReportEntryAccess().getLeftSquareBracketKeyword_3());
    }
	otherlv_5='target' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getResultReportEntryAccess().getTargetKeyword_4());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportEntryRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getResultReportEntryAccess().getTargetEObjectCrossReference_5_0()); 
	    }
		ruleURIID		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_7='description' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getResultReportEntryAccess().getDescriptionKeyword_6_0());
    }
(
(
		lv_decription_8_0=RULE_STRING
		{
			newLeafNode(lv_decription_8_0, grammarAccess.getResultReportEntryAccess().getDecriptionSTRINGTerminalRuleCall_6_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportEntryRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"decription",
        		lv_decription_8_0, 
        		"STRING");
	    }

)
))?(	otherlv_9='heading' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getResultReportEntryAccess().getHeadingKeyword_7_0());
    }
(
(
		lv_heading_10_0=RULE_STRING
		{
			newLeafNode(lv_heading_10_0, grammarAccess.getResultReportEntryAccess().getHeadingSTRINGTerminalRuleCall_7_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultReportEntryRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"heading",
        		lv_heading_10_0, 
        		"STRING");
	    }

)
))?(	otherlv_11='result' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getResultReportEntryAccess().getResultKeyword_8_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultReportEntryAccess().getResultDataResultDataParserRuleCall_8_1_0()); 
	    }
		lv_resultData_12_0=ruleResultData		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultReportEntryRule());
	        }
       		set(
       			$current, 
       			"resultData",
        		lv_resultData_12_0, 
        		"ResultData");
	        afterParserOrEnumRuleCall();
	    }

)
))?(	otherlv_13='issues' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getResultReportEntryAccess().getIssuesKeyword_9_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultReportEntryAccess().getIssueReportIssueParserRuleCall_9_1_0()); 
	    }
		lv_issue_14_0=ruleReportIssue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultReportEntryRule());
	        }
       		add(
       			$current, 
       			"issue",
        		lv_issue_14_0, 
        		"ReportIssue");
	        afterParserOrEnumRuleCall();
	    }

)
)*)?(	otherlv_15='[' 
    {
    	newLeafNode(otherlv_15, grammarAccess.getResultReportEntryAccess().getLeftSquareBracketKeyword_10_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultReportEntryAccess().getRowResultContributorParserRuleCall_10_1_0()); 
	    }
		lv_row_16_0=ruleResultContributor		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultReportEntryRule());
	        }
       		add(
       			$current, 
       			"row",
        		lv_row_16_0, 
        		"ResultContributor");
	        afterParserOrEnumRuleCall();
	    }

)
)+	otherlv_17=']' 
    {
    	newLeafNode(otherlv_17, grammarAccess.getResultReportEntryAccess().getRightSquareBracketKeyword_10_2());
    }
)?	otherlv_18=']' 
    {
    	newLeafNode(otherlv_18, grammarAccess.getResultReportEntryAccess().getRightSquareBracketKeyword_11());
    }
)
;





// Entry rule entryRuleResultContributor
entryRuleResultContributor returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getResultContributorRule()); }
	 iv_ruleResultContributor=ruleResultContributor 
	 { $current=$iv_ruleResultContributor.current; } 
	 EOF 
;

// Rule ResultContributor
ruleResultContributor returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='contributor' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getResultContributorAccess().getContributorKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultContributorRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getResultContributorAccess().getContributorEObjectCrossReference_1_0()); 
	    }
		ruleURIID		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='data' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getResultContributorAccess().getDataKeyword_2());
    }
(
(
		lv_cell_3_0=RULE_STRING
		{
			newLeafNode(lv_cell_3_0, grammarAccess.getResultContributorAccess().getCellSTRINGTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getResultContributorRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"cell",
        		lv_cell_3_0, 
        		"STRING");
	    }

)
)+(	otherlv_4='issues' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getResultContributorAccess().getIssuesKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultContributorAccess().getIssueReportIssueParserRuleCall_4_1_0()); 
	    }
		lv_issue_5_0=ruleReportIssue		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultContributorRule());
	        }
       		add(
       			$current, 
       			"issue",
        		lv_issue_5_0, 
        		"ReportIssue");
	        afterParserOrEnumRuleCall();
	    }

)
)*)?(	otherlv_6='[' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getResultContributorAccess().getLeftSquareBracketKeyword_5_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultContributorAccess().getSubcontributorResultContributorParserRuleCall_5_1_0()); 
	    }
		lv_subcontributor_7_0=ruleResultContributor		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultContributorRule());
	        }
       		add(
       			$current, 
       			"subcontributor",
        		lv_subcontributor_7_0, 
        		"ResultContributor");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_8=']' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getResultContributorAccess().getRightSquareBracketKeyword_5_2());
    }
)?)
;





// Entry rule entryRuleReportIssue
entryRuleReportIssue returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getReportIssueRule()); }
	 iv_ruleReportIssue=ruleReportIssue 
	 { $current=$iv_ruleReportIssue.current; } 
	 EOF 
;

// Rule ReportIssue
ruleReportIssue returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getReportIssueAccess().getIssueTypeReportIssueTypeEnumRuleCall_0_0()); 
	    }
		lv_issueType_0_0=ruleReportIssueType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getReportIssueRule());
	        }
       		set(
       			$current, 
       			"issueType",
        		lv_issueType_0_0, 
        		"ReportIssueType");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		lv_title_1_0=RULE_STRING
		{
			newLeafNode(lv_title_1_0, grammarAccess.getReportIssueAccess().getTitleSTRINGTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getReportIssueRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"title",
        		lv_title_1_0, 
        		"STRING");
	    }

)
)(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getReportIssueRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getReportIssueAccess().getTargetEObjectCrossReference_2_0()); 
	    }
		ruleURIID		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleResultData
entryRuleResultData returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getResultDataRule()); }
	 iv_ruleResultData=ruleResultData 
	 { $current=$iv_ruleResultData.current; } 
	 EOF 
;

// Rule ResultData
ruleResultData returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getResultDataAccess().getDataNameValuePairParserRuleCall_0()); 
	    }
		lv_data_0_0=ruleNameValuePair		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getResultDataRule());
	        }
       		add(
       			$current, 
       			"data",
        		lv_data_0_0, 
        		"NameValuePair");
	        afterParserOrEnumRuleCall();
	    }

)
)+
;





// Entry rule entryRuleNameValuePair
entryRuleNameValuePair returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getNameValuePairRule()); }
	 iv_ruleNameValuePair=ruleNameValuePair 
	 { $current=$iv_ruleNameValuePair.current; } 
	 EOF 
;

// Rule NameValuePair
ruleNameValuePair returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		lv_name_0_0=RULE_ID
		{
			newLeafNode(lv_name_0_0, grammarAccess.getNameValuePairAccess().getNameIDTerminalRuleCall_0_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getNameValuePairRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"ID");
	    }

)
)	otherlv_1='=' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getNameValuePairAccess().getEqualsSignKeyword_1());
    }
(
(
		lv_value_2_0=RULE_STRING
		{
			newLeafNode(lv_value_2_0, grammarAccess.getNameValuePairAccess().getValueSTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getNameValuePairRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_2_0, 
        		"STRING");
	    }

)
))
;





// Entry rule entryRuleURIID
entryRuleURIID returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getURIIDRule()); } 
	 iv_ruleURIID=ruleURIID 
	 { $current=$iv_ruleURIID.current.getText(); }  
	 EOF 
;

// Rule URIID
ruleURIID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
    this_STRING_0=RULE_STRING    {
		$current.merge(this_STRING_0);
    }

    { 
    newLeafNode(this_STRING_0, grammarAccess.getURIIDAccess().getSTRINGTerminalRuleCall()); 
    }

    ;





// Rule ReportIssueType
ruleReportIssueType returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='error' 
	{
        $current = grammarAccess.getReportIssueTypeAccess().getERROREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getReportIssueTypeAccess().getERROREnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='warning' 
	{
        $current = grammarAccess.getReportIssueTypeAccess().getWARNINGEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getReportIssueTypeAccess().getWARNINGEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='success' 
	{
        $current = grammarAccess.getReportIssueTypeAccess().getSUCCESSEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getReportIssueTypeAccess().getSUCCESSEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='info' 
	{
        $current = grammarAccess.getReportIssueTypeAccess().getINFOEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getReportIssueTypeAccess().getINFOEnumLiteralDeclaration_3()); 
    }
));



RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

