<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Dashboard</title>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Source+Sans+Pro:wght@300;400;600&display=swap" rel="stylesheet">
<style>
    :root {
        --primary-color: #2c3e50;
        --secondary-color: #34495e;
        --accent-color: #c0392b;
        --text-color: #333333;
        --background-color: #ecf0f1;
        --card-background: #ffffff;
    }

    body {
        font-family: 'Source Sans Pro', sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        min-height: 100vh;
        background-color: var(--background-color);
        color: var(--text-color);
    }

    nav {
        background-color: var(--primary-color);
        color: #ffffff;
        padding: 1rem 2rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: sticky;
        top: 0;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    nav h1 {
        margin: 0;
        font-size: 1.75rem;
        font-weight: 700;
        font-family: 'Playfair Display', serif;
    }

    .nav-links {
        display: flex;
        gap: 1rem;
    }

    .nav-links a {
        color: #ffffff;
        text-decoration: none;
        font-weight: 600;
        padding: 0.5rem 1rem;
        border-radius: 5px;
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    .nav-links a:hover {
        background-color: var(--secondary-color);
        transform: translateY(-2px);
    }

    .content {
        padding: 2rem;
        flex-grow: 1;
    }

    .stats {
        display: flex;
        gap: 2rem;
        justify-content: space-between;
        margin-bottom: 2rem;
    }

    .stat-box {
        background-color: var(--card-background);
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        padding: 1.5rem;
        flex: 1;
        text-align: center;
        transition: transform 0.3s ease;
    }

    .stat-box:hover {
        transform: translateY(-5px);
    }

    .stat-box h2 {
        margin-top: 0;
        font-size: 1.25rem;
        color: var(--secondary-color);
        font-weight: 600;
        font-family: 'Playfair Display', serif;
    }

    .stat-box p {
        font-size: 2.5rem;
        color: var(--accent-color);
        margin: 0.5rem 0 0;
        font-weight: 700;
    }

    .gallery {
        display: flex;
        gap: 1.5rem;
        margin-bottom: 2rem;
    }

    .gallery img {
        width: 100%;
        height: 250px;
        object-fit: fit;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }

    .gallery img:hover {
        transform: scale(1.05);
    }

    .info-card {
        background-color: var(--card-background);
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        padding: 2rem;
        margin-bottom: 2rem;
    }

    .info-card h2 {
        margin-top: 0;
        font-size: 1.5rem;
        color: var(--secondary-color);
        font-weight: 700;
        margin-bottom: 1rem;
        font-family: 'Playfair Display', serif;
    }

    .info-card p, .info-card ul {
        color: var(--text-color);
        line-height: 1.6;
    }

    .info-card ul {
        padding-left: 1.5rem;
    }

    .info-card li {
        margin-bottom: 0.5rem;
    }

    footer {
        background-color: var(--primary-color);
        color: #ffffff;
        text-align: center;
        padding: 1rem;
        margin-top: auto;
    }

    footer p {
        margin: 0;
        font-size: 0.9rem;
        font-weight: 300;
    }
</style>
</head>
<body>
    <nav>
        <h1>Admin Dashboard</h1>
        <div class="nav-links">
            <a href="UnderwriterRegistration.jsp">Registration</a>
            <a href="searchUnderwriter.jsp">Search</a>
            <a href="updateUnderwriterPassword.jsp">Update</a>
            <a href="deleteUnderwriter.jsp">Delete</a>
            <a href="AdminServlet?action=View All Underwriters">View Underwriters</a>
            <a href="viewAllVehicles.jsp">View Vehicles</a>
            <a href="index.html">Logout</a>
        </div>
    </nav>

    <div class="content">
        <div class="stats">
            <div class="stat-box">
                <h2>Total Underwriters</h2>
                <p>${totalUnderwriters}</p>
            </div>
            <div class="stat-box">
                <h2>Total Vehicles</h2>
                <p>${totalVehicles}</p>
            </div>
        </div>

        <div class="gallery">
            <img src="twoWheeler.jpeg" alt="Car Image 1">
            <img src="insurance.png" alt="Car Image 2">
            <img src="fourWheeler.jpg" alt="Car Image 3">
        </div>

        <div class="info-card">
            <h2>What You Can Do</h2>
            <p>As an administrator of the Vehicle Insurance Management System, you have access to a wide range of functionalities that allow you to efficiently manage and oversee all operations within the system. Here are the key tasks you can perform:</p>
            <ul>
                <li><strong>Underwriter Registration:</strong> Add new underwriters to the system by providing their details. This ensures that only authorized personnel can manage vehicle insurance policies.</li>
                <li><strong>Search Underwriters:</strong> Quickly find underwriters by their ID. This feature allows you to easily retrieve and view the information of registered underwriters.</li>
                <li><strong>Update Underwriter Password:</strong> Maintain security by updating passwords for underwriters. This helps in keeping the system secure and up to date.</li>
                <li><strong>Delete Underwriters:</strong> Remove underwriters from the system if they are no longer associated with your organization. This keeps the database clean and relevant.</li>
                <li><strong>View All Underwriters:</strong> View a complete list of all underwriters registered in the system. This gives you a quick overview of the team managing vehicle insurance policies.</li>
                <li><strong>View All Vehicles:</strong> Access a comprehensive list of all vehicles insured under the system. This is essential for tracking and managing vehicle policies.</li>
                <li><strong>Logout:</strong> Securely log out from the admin dashboard to ensure that the system remains protected when not in use.</li>
            </ul>
            <p>With these functionalities, you have the tools needed to manage underwriters and vehicles efficiently, ensuring that the system runs smoothly and securely.</p>
        </div>
    </div>

    <footer>
        <p>Developed by Group 27 | © 2024 Insurance Management System</p>
        <p> For ILP training batch 27th June.
        
    </footer>
</body>
</html>