package com.reunioes.api.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import com.reunioes.api.dtos.UsuarioDto;
import com.reunioes.api.dtos.ReuniaoDto;
import com.reunioes.api.dtos.PresencaDto;
import com.reunioes.api.dtos.RegraDto;
import com.reunioes.api.entities.Usuario;
import com.reunioes.api.entities.Reuniao;
import com.reunioes.api.entities.Presenca;
import com.reunioes.api.entities.Regra;
 
public class ConversaoUtils {
 
   	public static Reuniao Converter(ReuniaoDto reuniaoDto) throws ParseException {
 
         	Reuniao reuniao = new Reuniao();
 
         	if (reuniaoDto.getId() != null && reuniaoDto.getId() != "")
                	reuniao.setId(Integer.parseInt(reuniaoDto.getId()));
 
         	reuniao.setNome(reuniaoDto.getNome());
         	reuniao.setData(new SimpleDateFormat("dd/MM/yyyy").parse(reuniaoDto.getData()));
         	reuniao.setFinalizado(Integer.parseInt(reuniaoDto.getFinalizado()));
         	reuniao.setAtivo(Integer.parseInt(reuniaoDto.getAtivo()));
 
         	Usuario usuario = new Usuario();
         	usuario.setId(Integer.parseInt(reuniaoDto.getUsuarioId()));
 
         	reuniao.setUsuario(usuario);
 
         	return reuniao;
 
   	}
   	
   	public static ReuniaoDto Converter(Reuniao reuniao) {
 
         	ReuniaoDto reuniaoDto = new ReuniaoDto();
         	
         	reuniaoDto.setId(String.valueOf(reuniao.getId()));
         	reuniaoDto.setNome(reuniao.getNome());
         	reuniaoDto.setData(reuniao.getData().toString());
         	reuniaoDto.setFinalizado(String.valueOf(reuniao.getFinalizado()));
         	reuniaoDto.setAtivo(String.valueOf(reuniao.getAtivo()));
         	reuniaoDto.setUsuarioId(String.valueOf(reuniao.getUsuario().getId()));
 
         	return reuniaoDto;
 
   	}
   	
   	public static List<ReuniaoDto> ConverterListaReuniao(List<Reuniao> lista){
         	
         	List<ReuniaoDto> lst = new ArrayList<ReuniaoDto>(lista.size());
         	
         	for (Reuniao reuniao : lista) {
                	lst.add(Converter(reuniao));
         	}
         	
         	return lst;
         	
   	}
 
   	public static Usuario Converter(UsuarioDto usuarioDto) {
 
         	Usuario usuario = new Usuario();
 
         	if (usuarioDto.getId() != null && usuarioDto.getId() != "")
                	usuario.setId(Integer.parseInt(usuarioDto.getId()));
 
         	usuario.setNome(usuarioDto.getNome());
         	usuario.setEmail(usuarioDto.getEmail());
         	usuario.setAtivo(Integer.parseInt(usuarioDto.getAtivo()));
         	
         	if (usuarioDto.getRegras() != null && usuarioDto.getRegras().size() > 0) {
         		 
            	usuario.setRegras(new ArrayList<Regra>());

            	for (RegraDto regraDto : usuarioDto.getRegras()) {

                   	Regra regra = new Regra();
                   	regra.setNome(regraDto.getNome());

                   	usuario.getRegras().add(regra);

            	}

     	}
 
         	return usuario;
 
   	}
   	
   	public static UsuarioDto Converter(Usuario usuario) {
 
         	UsuarioDto usuarioDto = new UsuarioDto();
 
         	usuarioDto.setId(Integer.toString(usuario.getId()));
         	usuarioDto.setNome(usuario.getNome());
         	usuarioDto.setEmail(usuario.getEmail());
         	usuarioDto.setAtivo(Integer.toString(usuario.getAtivo()));
         	
         	if (usuario.getRegras() != null) {
            	
            	usuarioDto.setRegras(new ArrayList<RegraDto>());

            	for (int i = 0; i < usuario.getRegras().size(); i++) {
                   	
                   	RegraDto regraDto = new RegraDto();
                   	
                   	regraDto.setNome(usuario.getRegras().get(i).getNome());
                   	regraDto.setDescricao(usuario.getRegras().get(i).getDescricao());
                   	regraDto.setAtivo(usuario.getRegras().get(i).getAtivo());
                   	
                   	usuarioDto.getRegras().add(regraDto);
                   	
            	}

     	}

         	return usuarioDto;
 
   	}
   	
   	public static RegraDto Converter(Regra regra) {
   	 
     	RegraDto regraDto = new RegraDto();

     	regraDto.setNome(regra.getNome());
     	regraDto.setDescricao(regra.getDescricao());
     	regraDto.setAtivo(regra.getAtivo());

     	return regraDto;

	}

	public static List<RegraDto> Converter(List<Regra> regras) {

     	List<RegraDto> regrasDto = new ArrayList<RegraDto>();

     	for (Regra regra : regras)
            	regrasDto.add(Converter(regra));

     	return regrasDto;

	}
   	
   	public static List<UsuarioDto> ConverterListaUsuario(List<Usuario> lista){
     	
     	List<UsuarioDto> lst = new ArrayList<UsuarioDto>(lista.size());
     	
     	for (Usuario usuario : lista) {
            	lst.add(Converter(usuario));
     	}
     	
     	return lst;
     	
	}

   	
   	public static Presenca Converter(PresencaDto presencaDto) throws ParseException {
   	 
     	Presenca presenca = new Presenca();

     	if (presencaDto.getId() != null && presencaDto.getId() != "")
            	presenca.setId(Integer.parseInt(presencaDto.getId()));

     	presenca.setPresente(Integer.parseInt(presencaDto.getPresente()));

     	Usuario usuario = new Usuario();
     	usuario.setId(Integer.parseInt(presencaDto.getUsuarioId()));

     	presenca.setUsuario(usuario);
     	
     	Reuniao reuniao = new Reuniao();
     	reuniao.setId(Integer.parseInt(presencaDto.getReuniaoId()));

     	presenca.setReuniao(reuniao);

     	return presenca;

	}
	
	public static PresencaDto Converter(Presenca presenca) {

     	PresencaDto presencaDto = new PresencaDto();
     	
     	presencaDto.setId(String.valueOf(presenca.getId()));
     	presencaDto.setPresente(String.valueOf(presenca.getPresente()));
     	presencaDto.setUsuarioId(String.valueOf(presenca.getUsuario().getId()));
     	presencaDto.setReuniaoId(String.valueOf(presenca.getReuniao().getId()));
     	
     	return presencaDto;

	}
	
public static List<PresencaDto> ConverterListaPresenca(List<Presenca> lista){
     	
     	List<PresencaDto> lst = new ArrayList<PresencaDto>(lista.size());
     	
     	for (Presenca presenca : lista) {
            	lst.add(Converter(presenca));
     	}
     	
     	return lst;
     	
	}
	
}