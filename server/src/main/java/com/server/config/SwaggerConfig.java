package com.server.config;

//@EnableSwagger2
public class SwaggerConfig {

	/*private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("삼삼오오 RESTful API").description("삼삼오오 API 스펙문서입니다.").version("1.0").build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		consumes.add("multipart/form-data");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}

	@Bean
	public Docket commonApi() {
		TypeResolver typeResolver = new TypeResolver();
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey()))
				// .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				// .additionalModels(typeResolver.resolve(SuccessfulResponseDTO.class))
				.genericModelSubstitutes(SuccessfulResponseDTO.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(SuccessfulResponseDTO.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.produces(getProduceContentTypes()).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.server.controller")).paths(PathSelectors.ant("/**"))
				.build();
	}
*/
}
