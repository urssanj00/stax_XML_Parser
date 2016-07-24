This parser is exploring the idea of STAX Parser.

STAX Parser works on Pull method, same as JDBC. Its very easy to implement and as per documents online its more efficient than SAX and DOM. Reason for that is it does not take whole files in memory like DOM and its not slow like SAX. 

I am trying to convert the xml in hierarchical Map structure, but till now I am exploring the logic for that. If anybody can help, most welcome. 

<?xml version="1.0"?>
<Zoo xmlns="http://www.animaldept.gov">		   
<Lion>					   
   <Type>Wild</Type>
   <Sound>Roar</Sound>
   <Jungle><Den>Home</Den></Jungle>
<!-- it will eat you man -->
</Lion>
</Zoo>

I want to convert above xml in map as below :

{Zoo={Zoo, {Lion={Lion, {Type={Type, Wild}, Sound={Sound=Roar}, Jungle={Jungle, Den={Den, Hone}}}}}

Thanks.
