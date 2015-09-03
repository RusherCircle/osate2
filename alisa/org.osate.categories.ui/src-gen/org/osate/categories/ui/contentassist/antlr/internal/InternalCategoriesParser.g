/*
 * generated by Xtext
 */
parser grammar InternalCategoriesParser;

options {
	tokenVocab=InternalCategoriesLexer;
	superClass=AbstractInternalContentAssistParser;
	
}

@header {
package org.osate.categories.ui.contentassist.antlr.internal; 

import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.osate.categories.services.CategoriesGrammarAccess;

}

@members {
 
 	private CategoriesGrammarAccess grammarAccess;
 	
 	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
 	
 	{
		tokenNameToValue.put("LeftSquareBracket", "'['");
		tokenNameToValue.put("RightSquareBracket", "']'");
		tokenNameToValue.put("LeftCurlyBracket", "'{'");
		tokenNameToValue.put("RightCurlyBracket", "'}'");
		tokenNameToValue.put("Selection", "'selection'");
		tokenNameToValue.put("Categories", "'categories'");
		tokenNameToValue.put("Requirement", "'requirement'");
		tokenNameToValue.put("Verification", "'verification'");
 	}
 	
    public void setGrammarAccess(CategoriesGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }

	@Override
    protected String getValueForTokenName(String tokenName) {
    	String result = tokenNameToValue.get(tokenName);
    	if (result == null)
    		result = tokenName;
    	return result;
    }
}




// Entry rule entryRuleCategories
entryRuleCategories 
:
{ before(grammarAccess.getCategoriesRule()); }
	 ruleCategories
{ after(grammarAccess.getCategoriesRule()); } 
	 EOF 
;

// Rule Categories
ruleCategories 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCategoriesAccess().getAlternatives()); }
(rule__Categories__Alternatives)
{ after(grammarAccess.getCategoriesAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleRequirementCategories
entryRuleRequirementCategories 
:
{ before(grammarAccess.getRequirementCategoriesRule()); }
	 ruleRequirementCategories
{ after(grammarAccess.getRequirementCategoriesRule()); } 
	 EOF 
;

// Rule RequirementCategories
ruleRequirementCategories 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getRequirementCategoriesAccess().getGroup()); }
(rule__RequirementCategories__Group__0)
{ after(grammarAccess.getRequirementCategoriesAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleVerificationCategories
entryRuleVerificationCategories 
:
{ before(grammarAccess.getVerificationCategoriesRule()); }
	 ruleVerificationCategories
{ after(grammarAccess.getVerificationCategoriesRule()); } 
	 EOF 
;

// Rule VerificationCategories
ruleVerificationCategories 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getVerificationCategoriesAccess().getGroup()); }
(rule__VerificationCategories__Group__0)
{ after(grammarAccess.getVerificationCategoriesAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleSelectionCategories
entryRuleSelectionCategories 
:
{ before(grammarAccess.getSelectionCategoriesRule()); }
	 ruleSelectionCategories
{ after(grammarAccess.getSelectionCategoriesRule()); } 
	 EOF 
;

// Rule SelectionCategories
ruleSelectionCategories 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getSelectionCategoriesAccess().getGroup()); }
(rule__SelectionCategories__Group__0)
{ after(grammarAccess.getSelectionCategoriesAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}





// Entry rule entryRuleRequirementCategory
entryRuleRequirementCategory 
:
{ before(grammarAccess.getRequirementCategoryRule()); }
	 ruleRequirementCategory
{ after(grammarAccess.getRequirementCategoryRule()); } 
	 EOF 
;

// Rule RequirementCategory
ruleRequirementCategory 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getRequirementCategoryAccess().getGroup()); }
(rule__RequirementCategory__Group__0)
{ after(grammarAccess.getRequirementCategoryAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleVerificationCategory
entryRuleVerificationCategory 
:
{ before(grammarAccess.getVerificationCategoryRule()); }
	 ruleVerificationCategory
{ after(grammarAccess.getVerificationCategoryRule()); } 
	 EOF 
;

// Rule VerificationCategory
ruleVerificationCategory 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getVerificationCategoryAccess().getGroup()); }
(rule__VerificationCategory__Group__0)
{ after(grammarAccess.getVerificationCategoryAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleSelectionCategory
entryRuleSelectionCategory 
:
{ before(grammarAccess.getSelectionCategoryRule()); }
	 ruleSelectionCategory
{ after(grammarAccess.getSelectionCategoryRule()); } 
	 EOF 
;

// Rule SelectionCategory
ruleSelectionCategory 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getSelectionCategoryAccess().getGroup()); }
(rule__SelectionCategory__Group__0)
{ after(grammarAccess.getSelectionCategoryAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleCatRef
entryRuleCatRef 
:
{ before(grammarAccess.getCatRefRule()); }
	 ruleCatRef
{ after(grammarAccess.getCatRefRule()); } 
	 EOF 
;

// Rule CatRef
ruleCatRef 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCatRefAccess().getIDTerminalRuleCall()); }
	RULE_ID
{ after(grammarAccess.getCatRefAccess().getIDTerminalRuleCall()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__Categories__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getRequirementCategoriesParserRuleCall_0()); }
	ruleRequirementCategories
{ after(grammarAccess.getCategoriesAccess().getRequirementCategoriesParserRuleCall_0()); }
)

    |(
{ before(grammarAccess.getCategoriesAccess().getVerificationCategoriesParserRuleCall_1()); }
	ruleVerificationCategories
{ after(grammarAccess.getCategoriesAccess().getVerificationCategoriesParserRuleCall_1()); }
)

    |(
{ before(grammarAccess.getCategoriesAccess().getSelectionCategoriesParserRuleCall_2()); }
	ruleSelectionCategories
{ after(grammarAccess.getCategoriesAccess().getSelectionCategoriesParserRuleCall_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__RequirementCategories__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategories__Group__0__Impl
	rule__RequirementCategories__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategories__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoriesAccess().getRequirementKeyword_0()); }

	Requirement 

{ after(grammarAccess.getRequirementCategoriesAccess().getRequirementKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategories__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategories__Group__1__Impl
	rule__RequirementCategories__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategories__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoriesAccess().getCategoriesKeyword_1()); }

	Categories 

{ after(grammarAccess.getRequirementCategoriesAccess().getCategoriesKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategories__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategories__Group__2__Impl
	rule__RequirementCategories__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategories__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoriesAccess().getLeftSquareBracketKeyword_2()); }

	LeftSquareBracket 

{ after(grammarAccess.getRequirementCategoriesAccess().getLeftSquareBracketKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategories__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategories__Group__3__Impl
	rule__RequirementCategories__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategories__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
(
{ before(grammarAccess.getRequirementCategoriesAccess().getCategoryAssignment_3()); }
(rule__RequirementCategories__CategoryAssignment_3)
{ after(grammarAccess.getRequirementCategoriesAccess().getCategoryAssignment_3()); }
)
(
{ before(grammarAccess.getRequirementCategoriesAccess().getCategoryAssignment_3()); }
(rule__RequirementCategories__CategoryAssignment_3)*
{ after(grammarAccess.getRequirementCategoriesAccess().getCategoryAssignment_3()); }
)
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategories__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategories__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategories__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoriesAccess().getRightSquareBracketKeyword_4()); }

	RightSquareBracket 

{ after(grammarAccess.getRequirementCategoriesAccess().getRightSquareBracketKeyword_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}












rule__VerificationCategories__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategories__Group__0__Impl
	rule__VerificationCategories__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoriesAccess().getVerificationKeyword_0()); }

	Verification 

{ after(grammarAccess.getVerificationCategoriesAccess().getVerificationKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategories__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategories__Group__1__Impl
	rule__VerificationCategories__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoriesAccess().getCategoriesKeyword_1()); }

	Categories 

{ after(grammarAccess.getVerificationCategoriesAccess().getCategoriesKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategories__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategories__Group__2__Impl
	rule__VerificationCategories__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoriesAccess().getLeftSquareBracketKeyword_2()); }

	LeftSquareBracket 

{ after(grammarAccess.getVerificationCategoriesAccess().getLeftSquareBracketKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategories__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategories__Group__3__Impl
	rule__VerificationCategories__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
(
{ before(grammarAccess.getVerificationCategoriesAccess().getCategoryAssignment_3()); }
(rule__VerificationCategories__CategoryAssignment_3)
{ after(grammarAccess.getVerificationCategoriesAccess().getCategoryAssignment_3()); }
)
(
{ before(grammarAccess.getVerificationCategoriesAccess().getCategoryAssignment_3()); }
(rule__VerificationCategories__CategoryAssignment_3)*
{ after(grammarAccess.getVerificationCategoriesAccess().getCategoryAssignment_3()); }
)
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategories__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategories__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoriesAccess().getRightSquareBracketKeyword_4()); }

	RightSquareBracket 

{ after(grammarAccess.getVerificationCategoriesAccess().getRightSquareBracketKeyword_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}












rule__SelectionCategories__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategories__Group__0__Impl
	rule__SelectionCategories__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoriesAccess().getSelectionKeyword_0()); }

	Selection 

{ after(grammarAccess.getSelectionCategoriesAccess().getSelectionKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategories__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategories__Group__1__Impl
	rule__SelectionCategories__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoriesAccess().getCategoriesKeyword_1()); }

	Categories 

{ after(grammarAccess.getSelectionCategoriesAccess().getCategoriesKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategories__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategories__Group__2__Impl
	rule__SelectionCategories__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoriesAccess().getLeftSquareBracketKeyword_2()); }

	LeftSquareBracket 

{ after(grammarAccess.getSelectionCategoriesAccess().getLeftSquareBracketKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategories__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategories__Group__3__Impl
	rule__SelectionCategories__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
(
{ before(grammarAccess.getSelectionCategoriesAccess().getCategoryAssignment_3()); }
(rule__SelectionCategories__CategoryAssignment_3)
{ after(grammarAccess.getSelectionCategoriesAccess().getCategoryAssignment_3()); }
)
(
{ before(grammarAccess.getSelectionCategoriesAccess().getCategoryAssignment_3()); }
(rule__SelectionCategories__CategoryAssignment_3)*
{ after(grammarAccess.getSelectionCategoriesAccess().getCategoryAssignment_3()); }
)
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategories__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategories__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoriesAccess().getRightSquareBracketKeyword_4()); }

	RightSquareBracket 

{ after(grammarAccess.getSelectionCategoriesAccess().getRightSquareBracketKeyword_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}












rule__RequirementCategory__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategory__Group__0__Impl
	rule__RequirementCategory__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getNameAssignment_0()); }
(rule__RequirementCategory__NameAssignment_0)
{ after(grammarAccess.getRequirementCategoryAccess().getNameAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategory__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategory__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getGroup_1()); }
(rule__RequirementCategory__Group_1__0)?
{ after(grammarAccess.getRequirementCategoryAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__RequirementCategory__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategory__Group_1__0__Impl
	rule__RequirementCategory__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }

	LeftCurlyBracket 

{ after(grammarAccess.getRequirementCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategory__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategory__Group_1__1__Impl
	rule__RequirementCategory__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getSubCategoriesAssignment_1_1()); }
(rule__RequirementCategory__SubCategoriesAssignment_1_1)*
{ after(grammarAccess.getRequirementCategoryAccess().getSubCategoriesAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RequirementCategory__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RequirementCategory__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getRightCurlyBracketKeyword_1_2()); }

	RightCurlyBracket 

{ after(grammarAccess.getRequirementCategoryAccess().getRightCurlyBracketKeyword_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__VerificationCategory__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategory__Group__0__Impl
	rule__VerificationCategory__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getNameAssignment_0()); }
(rule__VerificationCategory__NameAssignment_0)
{ after(grammarAccess.getVerificationCategoryAccess().getNameAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategory__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategory__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getGroup_1()); }
(rule__VerificationCategory__Group_1__0)?
{ after(grammarAccess.getVerificationCategoryAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__VerificationCategory__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategory__Group_1__0__Impl
	rule__VerificationCategory__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }

	LeftCurlyBracket 

{ after(grammarAccess.getVerificationCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategory__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategory__Group_1__1__Impl
	rule__VerificationCategory__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getSubCategoriesAssignment_1_1()); }
(rule__VerificationCategory__SubCategoriesAssignment_1_1)*
{ after(grammarAccess.getVerificationCategoryAccess().getSubCategoriesAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__VerificationCategory__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__VerificationCategory__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getRightCurlyBracketKeyword_1_2()); }

	RightCurlyBracket 

{ after(grammarAccess.getVerificationCategoryAccess().getRightCurlyBracketKeyword_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__SelectionCategory__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategory__Group__0__Impl
	rule__SelectionCategory__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getNameAssignment_0()); }
(rule__SelectionCategory__NameAssignment_0)
{ after(grammarAccess.getSelectionCategoryAccess().getNameAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategory__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategory__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getGroup_1()); }
(rule__SelectionCategory__Group_1__0)?
{ after(grammarAccess.getSelectionCategoryAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__SelectionCategory__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategory__Group_1__0__Impl
	rule__SelectionCategory__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }

	LeftCurlyBracket 

{ after(grammarAccess.getSelectionCategoryAccess().getLeftCurlyBracketKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategory__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategory__Group_1__1__Impl
	rule__SelectionCategory__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getSubCategoriesAssignment_1_1()); }
(rule__SelectionCategory__SubCategoriesAssignment_1_1)*
{ after(grammarAccess.getSelectionCategoryAccess().getSubCategoriesAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SelectionCategory__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SelectionCategory__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getRightCurlyBracketKeyword_1_2()); }

	RightCurlyBracket 

{ after(grammarAccess.getSelectionCategoryAccess().getRightCurlyBracketKeyword_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}









rule__RequirementCategories__CategoryAssignment_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoriesAccess().getCategoryRequirementCategoryParserRuleCall_3_0()); }
	ruleRequirementCategory{ after(grammarAccess.getRequirementCategoriesAccess().getCategoryRequirementCategoryParserRuleCall_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategories__CategoryAssignment_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoriesAccess().getCategoryVerificationCategoryParserRuleCall_3_0()); }
	ruleVerificationCategory{ after(grammarAccess.getVerificationCategoriesAccess().getCategoryVerificationCategoryParserRuleCall_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategories__CategoryAssignment_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoriesAccess().getCategorySelectionCategoryParserRuleCall_3_0()); }
	ruleSelectionCategory{ after(grammarAccess.getSelectionCategoriesAccess().getCategorySelectionCategoryParserRuleCall_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__NameAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
	RULE_ID{ after(grammarAccess.getRequirementCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__RequirementCategory__SubCategoriesAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRequirementCategoryAccess().getSubCategoriesRequirementCategoryCrossReference_1_1_0()); }
(
{ before(grammarAccess.getRequirementCategoryAccess().getSubCategoriesRequirementCategoryCatRefParserRuleCall_1_1_0_1()); }
	ruleCatRef{ after(grammarAccess.getRequirementCategoryAccess().getSubCategoriesRequirementCategoryCatRefParserRuleCall_1_1_0_1()); }
)
{ after(grammarAccess.getRequirementCategoryAccess().getSubCategoriesRequirementCategoryCrossReference_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__NameAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
	RULE_ID{ after(grammarAccess.getVerificationCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__VerificationCategory__SubCategoriesAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVerificationCategoryAccess().getSubCategoriesVerificationCategoryCrossReference_1_1_0()); }
(
{ before(grammarAccess.getVerificationCategoryAccess().getSubCategoriesVerificationCategoryCatRefParserRuleCall_1_1_0_1()); }
	ruleCatRef{ after(grammarAccess.getVerificationCategoryAccess().getSubCategoriesVerificationCategoryCatRefParserRuleCall_1_1_0_1()); }
)
{ after(grammarAccess.getVerificationCategoryAccess().getSubCategoriesVerificationCategoryCrossReference_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__NameAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
	RULE_ID{ after(grammarAccess.getSelectionCategoryAccess().getNameIDTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SelectionCategory__SubCategoriesAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSelectionCategoryAccess().getSubCategoriesSelectionCategoryCrossReference_1_1_0()); }
(
{ before(grammarAccess.getSelectionCategoryAccess().getSubCategoriesSelectionCategoryCatRefParserRuleCall_1_1_0_1()); }
	ruleCatRef{ after(grammarAccess.getSelectionCategoryAccess().getSubCategoriesSelectionCategoryCatRefParserRuleCall_1_1_0_1()); }
)
{ after(grammarAccess.getSelectionCategoryAccess().getSubCategoriesSelectionCategoryCrossReference_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

