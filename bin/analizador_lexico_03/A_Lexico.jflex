package analizador_lexico_03;
import static analizador_lexico_03.Tokens.*;
%%
%class A_Lexico
%type Tokens
%line
%char
%column

%{
	public String lexeme;
%}



L=[a-zA-Z_]+
ID=[a-zA-Z][a-zA-Z0-9_]*
D=[0-9]+
T=[;]
espacio=[ ,\t,\r,\n]+

SELECT=(SELECT|select)
FROM=(FROM|from)
WHERE=(WHERE|where)
ORDERBY=((ORDER)(" ")(BY)|(order)(" ")(by))
HAVING=(HAVING|having)
GROUPBY=((GROUP)(" ")(BY)|(group)(" ")(by))
INSERT=(INSERT|insert)
INTO=(INTO|into)
VALUES=(VALUES|values)
UPDATE=(UPDATE|update)
SET=(SET|set)
DELETE=(DELETE|delete)



%%

{SELECT} {return SELECT;}
{FROM} {return FROM;}
{WHERE} {return WHERE;}
{ORDERBY} {return ORDERBY;}
{HAVING} {return HAVING;}
{GROUPBY} {return GROUPBY;}
{INSERT} {return INSERT;}
{INTO} {return INTO;}
{VALUES} {return VALUES;}
{UPDATE} {return UPDATE;}
{SET} {return SET;}
{DELETE} {return DELETE;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"="  {return Igual;}
"+"  {return Suma;}
"-"  {return Resta;}
"*"  {return Multiplicacion;}
"/"  {return Division;}
";"	 {lexeme=yytext(); return Terminacion;}

{ID} {lexeme=yytext(); return Identificador;}
/*{L}({L}|{D})* {lexeme=yytext(); return Identificador;}*/
("(-"{D}+")") | {D}+ {lexeme=yytext(); return Numero;}
(SELECT|select)(" ")([*]|[a-zA-Z][a-zA-Z0-9]*)(" ")(FROM|from)(" ")[a-zA-Z][a-zA-Z0-9]*(" ")*((WHERE|where)(" ")[a-zA-Z][a-zA-Z0-9]*(" ")*(=)(" ")*[a-zA-Z0-9]*)*((" ")*(((G)(R)(O)(U)(P)(" ")(B)(Y)|(g)(r)(o)(u)(p)(" ")(b)(y))|(HAVING|having)|((O)(R)(D)(E)(R)(" ")(B)(Y))|(o)(r)(d)(e)(r)(" ")(b)(y))(" ")[a-zA-Z0-9]+(" ")*(=)(" ")*[a-zA-Z0-9]+)*(;) {lexeme=yytext(); return Sentencia_SELECT;}
(INSERT|insert)(" ")(INTO|into)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")+([(]([a-zA-Z0-9]+(,)*)+[)])*(" ")*(VALUES|values)(" ")([(]([a-zA-Z0-9]+(,)*)+[)])+(;) {lexeme=yytext(); return Sentencia_INSERT;}
(UPDATE|update)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")(SET|set)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")*(=)(" ")*([a-zA-Z0-9]+)(" ")(WHERE|where)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")*(=)(" ")*([a-zA-Z0-9]+)(;) {lexeme=yytext(); return Sentencia_UPDATE;}
(DELETE|delete)(" ")(FROM|from)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")(WHERE|where)(" ")([a-zA-Z][a-zA-Z0-9]*)(" ")*(=)(" ")*[a-zA-Z0-9]*(;) {lexeme=yytext(); return Sentencia_DELETE;}
. {lexeme = "Error: en la linea: "+(yyline+1)+" y columna: "+(yycolumn+1);
return ERROR;}





