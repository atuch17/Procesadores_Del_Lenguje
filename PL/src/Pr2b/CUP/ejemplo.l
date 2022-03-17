package Pr2b.CUP;

%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
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
op100 = \%
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
{numInt}          {return ops.unidadNumInt();}
{numReal}         {return ops.unidadNumReal();}
{sepSec}          {return ops.unidadSepSec();}
{sepIns}          {return ops.unidadSepIns();}
{asig}            {return ops.unidadAsig();}
{pAp}             {return ops.unidadPAp();}
{pCierre}         {return ops.unidadPCierre();}
{opSuma}          {return ops.unidadSuma();}
{opResta}         {return ops.unidadResta();}
{opMult}          {return ops.unidadMult();}
{opDiv}           {return ops.unidadDiv();}
{opMenor}         {return ops.unidadMenor();}
{opMayor}         {return ops.unidadMayor();}
{opMenIg}         {return ops.unidadMenorIgual();}
{opMayIg}         {return ops.unidadMayorIgual();}
{opComp}          {return ops.unidadComparador();}
{opDist}          {return ops.unidadDistinto();}
{op100}           {return ops.unidadPorcentaje();}
{corcheteAp}      {return ops.unidadCorcheteAp();}
{corcheteCierre}  {return ops.unidadCorcheteCierre();}
{llavAp}          {return ops.unidadLlaveAp();}
{llaveCierre}     {return ops.unidadLlaveCierre();}
{punto}           {return ops.unidadPunto();}
{opFlecha}        {return ops.unidadFlecha();}
{coma}            {return ops.unidadComa();}
{opAmp}           {return ops.unidadAmp();}
{int}             {return ops.unidadInt();}
{real}            {return ops.unidadReal();}
{bool}            {return ops.unidadBool();}
{true}            {return ops.unidadTrue();}
{false}           {return ops.unidadFalse();}
{and}             {return ops.unidadAnd();}
{or}              {return ops.unidadOr();}
{not}             {return ops.unidadNot();}
{string}          {return ops.unidadString();}
{null}            {return ops.unidadNull();}
{proc}            {return ops.unidadProc();}
{if}              {return ops.unidadIf();}
{then}            {return ops.unidadThen();}
{else}            {return ops.unidadElse();}
{endif}           {return ops.unidadEndif();}
{while}           {return ops.unidadWhile();}
{do}              {return ops.unidadDo();}
{endwhile}        {return ops.unidadEndwhile();}
{call}            {return ops.unidadCall();}
{record}          {return ops.unidadRecord();}
{array}           {return ops.unidadArray();}
{of}              {return ops.unidadOf();}
{pointer}         {return ops.unidadPointer();}
{new}             {return ops.unidadNew();}
{delete}          {return ops.unidadDelete();}
{read}            {return ops.unidadRead();}
{write}           {return ops.unidadWrite();}
{nl}              {return ops.unidadNl();}
{var}             {return ops.unidadVar();}
{type}            {return ops.unidadType();}
{cadena}          {return ops.unidadCadena();}
{id}              {return ops.unidadId();}
[^]               {errores.errorLexico(fila(),lexema());}