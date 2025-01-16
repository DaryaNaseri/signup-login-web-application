package ir.maktabsharif.usersignuploginapplication.util;

import ir.maktabsharif.usersignuploginapplication.model.entity.Permission;
import ir.maktabsharif.usersignuploginapplication.model.entity.UserRole;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ReadXml {
    private static List<UserRole> globalRoles = new ArrayList<>();
    private static List<Permission> permissions = new ArrayList<>();

    public static List<UserRole> getGlobalRoles() {
        return globalRoles;
    }

    public static List<Permission> getPermissions() {
        return permissions;
    }

    public static void readXml() {

        try {
            File fXmlFile = new File("C:\\Users\\Darya\\IdeaProjects\\UserSignupLoginApplication\\src\\main\\resources\\application-config.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            Element appElement = doc.getDocumentElement();

            NodeList securityList = appElement.getElementsByTagName("security");

            Element security = (Element) securityList.item(0);

            NodeList rolesList = appElement.getElementsByTagName("roles");

            Element roles = (Element) rolesList.item(0);

            NodeList rolesChild = roles.getChildNodes();



            for (int i = 0; i < rolesChild.getLength(); i++) {
                Node roleNode = rolesChild.item(i);

                if (roleNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element role = (Element) roleNode;

                    String roleName = role.getNodeName();

                    NodeList permissionList = role.getElementsByTagName("permissions");

                    Element permissions = (Element) permissionList.item(0);

                    NodeList permissionsChild = permissions.getChildNodes();

                    List<String> permissionsName = new ArrayList<>();

                    for (int j = 0; j < permissionsChild.getLength(); j++) {
                        Node permissionNode = permissionsChild.item(j);

                        if (permissionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element permission = (Element) permissionNode;

                            String permissionTextContent = permission.getTextContent().trim();

                            permissionsName.add(permissionTextContent);


                        }
                    }
                    List<Permission> rolePermissionList = makeListOfPermissions(permissionsName);
                    UserRole userRole = UserRole.builder().roleName(roleName).permissions(rolePermissionList).build();
                    globalRoles.add(userRole);
                }

            }
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static List<Permission> makeListOfPermissions(List<String> permissionsName) {
        List<Permission> result = new ArrayList<>();

        outer:
        for (String permissionName : permissionsName) {
            inner:
            for (Permission item : permissions) {
                if (permissionName.equals(item.getPermissionName())) {
                    result.add(item);
                    continue outer;
                }
            }
            Permission permission = Permission.builder().permissionName(permissionName).build();
            result.add(permission);
            permissions.add(permission);
        }

        return result;
    }


}
