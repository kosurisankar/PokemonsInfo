package com.skworks.pokeinfo.client

import retrofit2.Call

class PokeApiClient(
    clientConfig: ClientConfig = ClientConfig()
) : PokeApi {

    private val service = PokeApiServiceImpl(clientConfig)

//    private fun <T> Call<T> : T {
//        return execute().let {
//            if (it.isSuccessful) it.body()!! else throw ErrorResponse(it.code(), it.message())
//        }
//    }

    // region ResourceList Lists

    // region Berries

     override suspend fun getBerryList(offset: Int, limit: Int) =
        service.getBerryList(offset, limit) 

     override suspend fun getBerryFirmnessList(offset: Int, limit: Int) =
        service.getBerryFirmnessList(offset, limit) 

     override suspend fun getBerryFlavorList(offset: Int, limit: Int) =
        service.getBerryFlavorList(offset, limit) 

    // endregion Berries

    // region Contests

     override suspend fun getContestTypeList(offset: Int, limit: Int) =
        service.getContestTypeList(offset, limit) 

     override suspend fun getContestEffectList(offset: Int, limit: Int) =
        service.getContestEffectList(offset, limit) 

     override suspend fun getSuperContestEffectList(offset: Int, limit: Int) =
        service.getSuperContestEffectList(offset, limit) 

    // endregion Contests

    // region Encounters

     override suspend fun getEncounterMethodList(offset: Int, limit: Int) =
        service.getEncounterMethodList(offset, limit) 

     override suspend fun getEncounterConditionList(offset: Int, limit: Int) =
        service.getEncounterConditionList(offset, limit) 

     override suspend fun getEncounterConditionValueList(offset: Int, limit: Int) =
        service.getEncounterConditionValueList(offset, limit) 

    // endregion

    // region Evolution

     override suspend fun getEvolutionChainList(offset: Int, limit: Int) =
        service.getEvolutionChainList(offset, limit) 

     override suspend fun getEvolutionTriggerList(offset: Int, limit: Int) =
        service.getEvolutionTriggerList(offset, limit) 

    // endregion

    //region Games

     override suspend fun getGenerationList(offset: Int, limit: Int) =
        service.getGenerationList(offset, limit) 

     override suspend fun getPokedexList(offset: Int, limit: Int) =
        service.getPokedexList(offset, limit) 

     override suspend fun getVersionList(offset: Int, limit: Int) =
        service.getVersionList(offset, limit) 

     override suspend fun getVersionGroupList(offset: Int, limit: Int) =
        service.getVersionGroupList(offset, limit) 

    // endregion

    // region Items

     override suspend fun getItemList(offset: Int, limit: Int) = service.getItemList(offset, limit) 

     override suspend fun getItemAttributeList(offset: Int, limit: Int) =
        service.getItemAttributeList(offset, limit) 

     override suspend fun getItemCategoryList(offset: Int, limit: Int) =
        service.getItemCategoryList(offset, limit) 

     override suspend fun getItemFlingEffectList(offset: Int, limit: Int) =
        service.getItemFlingEffectList(offset, limit) 

     override suspend fun getItemPocketList(offset: Int, limit: Int) =
        service.getItemPocketList(offset, limit) 

    // endregion

    //region Moves

     override suspend fun getMoveList(offset: Int, limit: Int) = service.getMoveList(offset, limit) 

     override suspend fun getMoveAilmentList(offset: Int, limit: Int) =
        service.getMoveAilmentList(offset, limit) 

     override suspend fun getMoveBattleStyleList(offset: Int, limit: Int) =
        service.getMoveBattleStyleList(offset, limit) 

     override suspend fun getMoveCategoryList(offset: Int, limit: Int) =
        service.getMoveCategoryList(offset, limit) 

     override suspend fun getMoveDamageClassList(offset: Int, limit: Int) =
        service.getMoveDamageClassList(offset, limit) 

     override suspend fun getMoveLearnMethodList(offset: Int, limit: Int) =
        service.getMoveLearnMethodList(offset, limit) 

     override suspend fun getMoveTargetList(offset: Int, limit: Int) =
        service.getMoveTargetList(offset, limit) 

    // endregion

    // region Locations

     override suspend fun getLocationList(offset: Int, limit: Int) =
        service.getLocationList(offset, limit) 

     override suspend fun getLocationAreaList(offset: Int, limit: Int) =
        service.getLocationAreaList(offset, limit) 

     override suspend fun getPalParkAreaList(offset: Int, limit: Int) =
        service.getPalParkAreaList(offset, limit) 

     override suspend fun getRegionList(offset: Int, limit: Int) =
        service.getRegionList(offset, limit) 

    // endregion

    // region Machines

     override suspend fun getMachineList(offset: Int, limit: Int) =
        service.getMachineList(offset, limit) 

    // endregion Machines

    // region Pokemon

     override suspend fun getAbilityList(offset: Int, limit: Int) =
        service.getAbilityList(offset, limit) 

     override suspend fun getCharacteristicList(offset: Int, limit: Int) =
        service.getCharacteristicList(offset, limit) 

     override suspend fun getEggGroupList(offset: Int, limit: Int) =
        service.getEggGroupList(offset, limit) 

     override suspend fun getGenderList(offset: Int, limit: Int) =
        service.getGenderList(offset, limit) 

     override suspend fun getGrowthRateList(offset: Int, limit: Int) =
        service.getGrowthRateList(offset, limit) 

     override suspend fun getNatureList(offset: Int, limit: Int) =
        service.getNatureList(offset, limit) 

     override suspend fun getPokeathlonStatList(offset: Int, limit: Int) =
        service.getPokeathlonStatList(offset, limit) 

     override suspend fun getPokemonList(offset: Int, limit: Int) =
        service.getPokemonList(offset, limit)

     override suspend fun getPokemonColorList(offset: Int, limit: Int) =
        service.getPokemonColorList(offset, limit) 

     override suspend fun getPokemonFormList(offset: Int, limit: Int) =
        service.getPokemonFormList(offset, limit) 

     override suspend fun getPokemonHabitatList(offset: Int, limit: Int) =
        service.getPokemonHabitatList(offset, limit) 

     override suspend fun getPokemonShapeList(offset: Int, limit: Int) =
        service.getPokemonShapeList(offset, limit) 

     override suspend fun getPokemonSpeciesList(offset: Int, limit: Int) =
        service.getPokemonSpeciesList(offset, limit) 

     override suspend fun getStatList(offset: Int, limit: Int) = service.getStatList(offset, limit) 

     override suspend fun getTypeList(offset: Int, limit: Int) = service.getTypeList(offset, limit) 

    // endregion

    // region Utility

     override suspend fun getLanguageList(offset: Int, limit: Int) =
        service.getLanguageList(offset, limit) 

    // endregion

    // endregion

    // region Berries

     override suspend fun getBerry(id: Int) = service.getBerry(id) 

     override suspend fun getBerryFirmness(id: Int) = service.getBerryFirmness(id) 

     override suspend fun getBerryFlavor(id: Int) = service.getBerryFlavor(id) 

    // endregion Berries

    // region Contests

     override suspend fun getContestType(id: Int) = service.getContestType(id) 

     override suspend fun getContestEffect(id: Int) = service.getContestEffect(id) 

     override suspend fun getSuperContestEffect(id: Int) = service.getSuperContestEffect(id) 

    // endregion Contests

    // region Encounters

     override suspend fun getEncounterMethod(id: Int) = service.getEncounterMethod(id) 

     override suspend fun getEncounterCondition(id: Int) = service.getEncounterCondition(id) 

     override suspend fun getEncounterConditionValue(id: Int) =
        service.getEncounterConditionValue(id) 

    // endregion Contests

    // region Evolution

     override suspend fun getEvolutionChain(id: Int) = service.getEvolutionChain(id) 

     override suspend fun getEvolutionTrigger(id: Int) = service.getEvolutionTrigger(id) 

    // endregion Evolution

    // region Games

     override suspend fun getGeneration(id: Int) = service.getGeneration(id) 

     override suspend fun getPokedex(id: Int) = service.getPokedex(id) 

     override suspend fun getVersion(id: Int) = service.getVersion(id) 

     override suspend fun getVersionGroup(id: Int) = service.getVersionGroup(id) 

    // endregion Games

    // region Items

     override suspend fun getItem(id: Int) = service.getItem(id) 

     override suspend fun getItemAttribute(id: Int) = service.getItemAttribute(id) 

     override suspend fun getItemCategory(id: Int) = service.getItemCategory(id) 

     override suspend fun getItemFlingEffect(id: Int) = service.getItemFlingEffect(id) 

     override suspend fun getItemPocket(id: Int) = service.getItemPocket(id) 

    // endregion Items

    // region Moves

     override suspend fun getMove(id: Int) = service.getMove(id) 

     override suspend fun getMoveAilment(id: Int) = service.getMoveAilment(id) 

     override suspend fun getMoveBattleStyle(id: Int) = service.getMoveBattleStyle(id) 

     override suspend fun getMoveCategory(id: Int) = service.getMoveCategory(id) 

     override suspend fun getMoveDamageClass(id: Int) = service.getMoveDamageClass(id) 

     override suspend fun getMoveLearnMethod(id: Int) = service.getMoveLearnMethod(id) 

     override suspend fun getMoveTarget(id: Int) = service.getMoveTarget(id) 

    // endregion Moves

    // region Locations

     override suspend fun getLocation(id: Int) = service.getLocation(id) 

     override suspend fun getLocationArea(id: Int) = service.getLocationArea(id) 

     override suspend fun getPalParkArea(id: Int) = service.getPalParkArea(id) 

     override suspend fun getRegion(id: Int) = service.getRegion(id) 

    // endregion Locations

    // region Machines

     override suspend fun getMachine(id: Int) = service.getMachine(id) 

    // endregion Machines

    // region Pokemon

     override suspend fun getAbility(id: Int) = service.getAbility(id) 

     override suspend fun getCharacteristic(id: Int) = service.getCharacteristic(id) 

     override suspend fun getEggGroup(id: Int) = service.getEggGroup(id) 

     override suspend fun getGender(id: Int) = service.getGender(id) 

     override suspend fun getGrowthRate(id: Int) = service.getGrowthRate(id) 

     override suspend fun getNature(id: Int) = service.getNature(id) 

     override suspend fun getPokeathlonStat(id: Int) = service.getPokeathlonStat(id) 

     override suspend fun getPokemon(id: Int) = service.getPokemon(id) 

     override suspend fun getPokemonEncounterList(id: Int) = service.getPokemonEncounterList(id) 

     override suspend fun getPokemonColor(id: Int) = service.getPokemonColor(id) 

     override suspend fun getPokemonForm(id: Int) = service.getPokemonForm(id) 

     override suspend fun getPokemonHabitat(id: Int) = service.getPokemonHabitat(id) 

     override suspend fun getPokemonShape(id: Int) = service.getPokemonShape(id) 

     override suspend fun getPokemonSpecies(id: Int) = service.getPokemonSpecies(id) 

     override suspend fun getStat(id: Int) = service.getStat(id) 

     override suspend fun getType(id: Int) = service.getType(id) 

    // endregion Pokemon

    // region Utility

     override suspend fun getLanguage(id: Int) = service.getLanguage(id) 

    // endregion Utility
}
