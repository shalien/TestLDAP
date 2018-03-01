package io.projetretro.testldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class TestLDAP {

    public static void main(String[] args) {
        new TestLDAP();
    }

    TestLDAP() {

        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.10.201:389");
        env.put(Context.SECURITY_PRINCIPAL, "oduparc@tec6.fr");
        env.put(Context.SECURITY_CREDENTIALS, "testtest");

        LdapContext ctx = null;

        try {
            ctx = new InitialLdapContext(env, null);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        Attributes attributes = null;
        try {
            attributes = ctx.getAttributes(ctx.getNameInNamespace());

            for(NamingEnumeration ae = attributes.getAll(); ae.hasMoreElements();) {

                Attribute attr = (Attribute)ae.next();
                String attrId = attr.getID();

                for (NamingEnumeration vals = attr.getAll(); vals.hasMore();) {
                    String thing = vals.next().toString();
                    System.out.println(attrId + ": " + thing);
                }

            }


        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
