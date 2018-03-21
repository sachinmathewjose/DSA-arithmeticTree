# DSA-arithmeticTree
parsing an equation and form its arithmetic equation and make a tree based on the rules


<expression>  ::=  <term> + <expression>  |  <term> - <expression>  |  <term>

<term>  ::=  <factor> * <term>  |  <factor> / <term>  |  <factor>

<factor>  ::=  <digit>  |  (  <expression>  )

<digit>  ::=   0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
