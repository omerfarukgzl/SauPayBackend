package com.omer.Payment.notes;

public class GeneralNotes {
    /*



    Equals Nedir?
        Equals metodu, bir nesnenin kendisine eşit olup olmadığını kontrol eder. Eğer iki nesne birbirine eşitse true değerini döndürür. Eğer iki nesne birbirine eşit değilse false değerini döndürür.

    HashCode Nedir?
        HashCode metodu ise bir nesnenin hash kodunu döndürür. Hash kod, bir nesnenin bellekteki adresini temsil eder. Bu yüzden iki nesne birbirine eşitse, hash kodları da birbirine eşit olur.

    Set, HashSet, HashMap gibi veriyapıları hash kodları kullanarak nesneleri eşitlik kontrolü yapar.
    Bu yüzden eğer bir nesne bir veriyapısına eklenmek isteniyorsa, bu nesnenin equals ve hashCode metotları override edilmelidir.

!!!!!! Hangi Model Class'larının Equals ve HashCode metotları override edilmesi gerekir?
        Bir entity nesnesi  private Set<Transaction> transactions  = new HashSet<>() ; şeklinde bir set içerisinde tutuluyorsa
        bu set içerisinde aynı id değerine sahip transaction nesnesi oluşmaması için bu metodları override etmemiz gerekir.

        Aynı şekilde bir entity nesnesi  private List<Transaction> transactions  = new ArrayList<>() ; şeklinde bir list içerisinde tutuluyorsa
        bu list içerisinde aynı id değerine sahip transaction nesnesi oluşmaması için bu metodları override etmemiz gerekir.












































     */
}
