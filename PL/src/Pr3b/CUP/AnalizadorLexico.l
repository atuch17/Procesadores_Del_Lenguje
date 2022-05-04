package Pr3b.CUP;

%%
%cup
%line
%class AnalizadorLexico
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErrores errores;
  private int col;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int col()  {return col;}
  public void incCol() {col += lexema().length();}
  public void fijaGestionErrores(GestionErrores errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.token(ClaseLexica.EOF);
%eofval}

%init{
  ops = new ALexOperations(this);
%init}


letra  = ([A-Z]|[a-z])
dPos = [1-9]
digito = ({dPos}|0)
punto = \.
pDec = {punto} {digito} ({digito}*{dPos})?
numInt = [\+\-]? (({dPos}{digito}*) | 0)
pExp = (e|E) {numInt}
numReal = {numInt}({pDec}{pExp}? |  {pExp})
sep = [ \t\r\b\n]
com = #[^\n]* 
and = and
or = or
not = not
string = string
null = null
proc = proc
if = if
then = then
else = else
true = true
endif = endif 
while = while
do = do
endwhile = endwhile 
call = call
record  = record 
int = int
real = real
bool = bool
false = false
array = array 
of = of
pointer = pointer
new = new
delete = delete
read = read
write = write
nl = nl
var = var
type = type
cadena = \"([^\"\r\b\n])*\"
id = {letra}({letra}|{digito}|_)*
sepSec = \&\&
sepIns = \;
asig = \=
pAp = \(
pCierre = \)
opSuma = \+
opResta = \-
opMult = \*
opDiv = \/
opMayor = \>
opMenor = \<
opMenIg = \<=
opMayIg = \>=
opComp = \==
opDist = \!=
opMod = \%
corcheteAp = \[
corcheteCierre = \]
llavAp = \{
llaveCierre = \}
punto = \.
opFlecha = \->
coma = \,
opAmp = \&

%%
{sep}             {}
{com}             {}
{numInt}          {return ops.token(ClaseLexica.NUM_INT);}
{numReal}         {return ops.token(ClaseLexica.NUM_REAL);}
{sepSec}          {return ops.token(ClaseLexica.SEP_SEC);}
{sepIns}          {return ops.token(ClaseLexica.SEP_INS);}
{asig}            {return ops.token(ClaseLexica.ASIG);}
{pAp}             {return ops.token(ClaseLexica.P_AP);}
{pCierre}         {return ops.token(ClaseLexica.P_CIERRE);}
{opSuma}          {return ops.token(ClaseLexica.OP_SUMA);}
{opResta}         {return ops.token(ClaseLexica.OP_RESTA);}
{opMult}          {return ops.token(ClaseLexica.OP_MULT);}
{opDiv}           {return ops.token(ClaseLexica.OP_DIV);}
{opMenor}         {return ops.token(ClaseLexica.OP_MENOR);}
{opMayor}         {return ops.token(ClaseLexica.OP_MAYOR);}
{opMenIg}         {return ops.token(ClaseLexica.OP_MEN_IG);}
{opMayIg}         {return ops.token(ClaseLexica.OP_MAY_IG);}
{opComp}          {return ops.token(ClaseLexica.OP_COMP);}
{opDist}          {return ops.token(ClaseLexica.OP_DIST);}
{opMod}           {return ops.token(ClaseLexica.OP_MOD);}
{corcheteAp}      {return ops.token(ClaseLexica.CORCHETE_AP);}
{corcheteCierre}  {return ops.token(ClaseLexica.CORCHETE_CIERRE);}
{llavAp}          {return ops.token(ClaseLexica.LLAVE_AP);}
{llaveCierre}     {return ops.token(ClaseLexica.LLAVE_CIERRE);}
{punto}           {return ops.token(ClaseLexica.PUNTO);}
{opFlecha}        {return ops.token(ClaseLexica.OP_FLECHA);}
{coma}            {return ops.token(ClaseLexica.COMA);}
{opAmp}           {return ops.token(ClaseLexica.OP_AMP);}
{int}             {return ops.token(ClaseLexica.INT);}
{real}            {return ops.token(ClaseLexica.REAL);}
{bool}            {return ops.token(ClaseLexica.BOOL);}
{true}            {return ops.token(ClaseLexica.TRUE);}
{false}           {return ops.token(ClaseLexica.FALSE);}
{and}             {return ops.token(ClaseLexica.AND);}
{or}              {return ops.token(ClaseLexica.OR);}
{not}             {return ops.token(ClaseLexica.NOT);}
{string}          {return ops.token(ClaseLexica.STRING);}
{null}            {return ops.token(ClaseLexica.NULL);}
{proc}            {return ops.token(ClaseLexica.PROC);}
{if}              {return ops.token(ClaseLexica.IF);}
{then}            {return ops.token(ClaseLexica.THEN);}
{else}            {return ops.token(ClaseLexica.ELSE);}
{endif}           {return ops.token(ClaseLexica.ENDIF);}
{while}           {return ops.token(ClaseLexica.WHILE);}
{do}              {return ops.token(ClaseLexica.DO);}
{endwhile}        {return ops.token(ClaseLexica.ENDWHILE);}
{call}            {return ops.token(ClaseLexica.CALL);}
{record}          {return ops.token(ClaseLexica.RECORD);}
{array}           {return ops.token(ClaseLexica.ARRAY);}
{of}              {return ops.token(ClaseLexica.OF);}
{pointer}         {return ops.token(ClaseLexica.POINTER);}
{new}             {return ops.token(ClaseLexica.NEW);}
{delete}          {return ops.token(ClaseLexica.DELETE);}
{read}            {return ops.token(ClaseLexica.READ);}
{write}           {return ops.token(ClaseLexica.WRITE);}
{nl}              {return ops.token(ClaseLexica.NL);}
{var}             {return ops.token(ClaseLexica.VAR);}
{type}            {return ops.token(ClaseLexica.TYPE);}
{cadena}          {return ops.token(ClaseLexica.CADENA);}
{id}              {return ops.token(ClaseLexica.ID);}
[^]               {errores.errorLexico(fila(),lexema());}