/**
 * Copyright 2011 Nikolche Mihajlovski
 *
 * This file is part of JAnnocessor.
 *
 * JAnnocessor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JAnnocessor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAnnocessor.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jannocessor.syntax;

import jsyntaxpane.Token;
import jsyntaxpane.TokenType;
import jsyntaxpane.lexers.DefaultJFlexLexer;

%%

%public
%class JavaTemplateLexer
%extends DefaultJFlexLexer
%unicode
%char
%type Token

%{
    public JavaTemplateLexer() {
        super();
    }

    public Token token(TokenType type) {
        return new Token(type, yychar, yylength());
    }

    public Token token(TokenType type, int pairValue) {
        return new Token(type, yychar, yylength(), (byte)pairValue);
    }

	@Override
	public int yychar() {
		return yychar;
	}

%}

WhiteSpace = {LineTerminator} | [ \t\f]+

Comment = {JavaLineComment} | {JavaBlockComment} | {VelocityLineComment}
LineTerminator = \r\n|\r|\n
NonLineTerminator = [^\r\n]
JavaBlockComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
JavaLineComment = "//" {NonLineTerminator}* {LineTerminator}?
VelocityLineComment = "##" {NonLineTerminator}* {LineTerminator}?

Letter = [a-zA-Z_]
Alphanumeric = [a-zA-Z0-9_]
Identifier = {Letter} {Alphanumeric}*
StringLiteral1 = "\""
StringLiteral2 = "'" [^\'\n\r]* "'"
NumericLiteral = [0-9]+ ("." [0-9]+)?

Macro = "#" {Letter} {Alphanumeric}*
Macro2 = "#" "@" {Letter} {Alphanumeric}*
Variable = "$" {Letter} {Alphanumeric}* ("." {Letter} {Alphanumeric}* ("(" [^()]*? ")")? )*
Expression = "$" "{" [^}]*? "}"

%%

<YYINITIAL> {

  "package" | "class" | "interface" | "enum" |
  "public" | "protected" | "private" | "final" | "abstract" |
  "static" | "strictfp" | "transient" | "volatile" | "synchronized" |
  "extends" | "implements" | "throws" | "switch" | "case" | "default" |
  "if" | "else" | "while" | "for" | "this" | "new" |
  "null" | "void" | "true" | "false" | "String" | "boolean" | "int" | "char" | "long" | "double" { return token(TokenType.KEYWORD); }

  "{" { return token(TokenType.OPERATOR, 10); }
  "}" { return token(TokenType.OPERATOR, -10); }
  "[" { return token(TokenType.OPERATOR, 20); }
  "]" { return token(TokenType.OPERATOR, -20); }
  "(" { return token(TokenType.OPERATOR, 30); }
  ")" { return token(TokenType.OPERATOR, -30); }

  ";" | "," | "." | "=" | "<" | "!" | "&&" | "+" | "-" | "*" { return token(TokenType.OPERATOR); }

  "#if" | "#elseif" | "#else" | "#foreach" | "#end" | "#define" | "#macro" { return token(TokenType.KEYWORD2); }

  "#evaluate" | "#break" | "#stop" | "#parse" | "#include" | "#set" { return token(TokenType.TYPE); }

  {Identifier} { return token(TokenType.IDENTIFIER); }
  {Macro} { return token(TokenType.TYPE2); }
  {Macro2} { return token(TokenType.KEYWORD2); }
  {Variable} { return token(TokenType.TYPE3); }
  {Expression} { return token(TokenType.TYPE3); }
  {NumericLiteral} { return token(TokenType.NUMBER); }
  {StringLiteral1} { return token(TokenType.STRING); }
  {StringLiteral2} { return token(TokenType.STRING2); }
  {Comment} { return token(TokenType.COMMENT); }
  {WhiteSpace} { }

}

.|\n {  }
<<EOF>> { return null; }
