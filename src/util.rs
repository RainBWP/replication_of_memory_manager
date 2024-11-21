use std::io::Read;
use std::process::Command;

//Herramienta para obtener una entrada xd
pub fn try_input<T: std::str::FromStr>(text: &str) -> Result<T, std::io::Error> {
    let mut entrada = String::new();
    let salida;
    loop {
        println!("{}", text);
        std::io::stdin().read_line(&mut entrada)?;
        salida = match entrada.trim().parse::<T>() {
            Ok(num) => num,
            Err(_) => {
                entrada.clear();
                continue;
            }
        };
        break;
    }
    Ok(salida)
}

//Herramienta para pausar por un momento la continuidad de la ejecición
pub fn pause() {
    println!("Press any key to continue...");
    let _ = std::io::stdin().read(&mut [0u8]).unwrap();
}

//Para limpiar la pantallla
pub fn clear() {
    if cfg!(target_os = "windows") {
        Command::new("cmd").arg("/c").arg("cls").status().unwrap();
    } else {
        Command::new("clear").status().unwrap();
    }
}
